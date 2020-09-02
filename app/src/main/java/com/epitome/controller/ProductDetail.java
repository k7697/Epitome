package com.epitome.controller;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.R;
import com.epitome.adapter.ProductDetailImagesListAdapter;
import com.epitome.adapter.RelatedProductsAdapter;
import com.epitome.model.Category;
import com.epitome.model.Product;
import com.epitome.response.ProductResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String productID;

    public ProductDetail(String productID) {
        this.productID = productID;
    }
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    boolean signedIn;
    ViewPager viewPager;
    ProductDetailImagesListAdapter proAdapter;
    int productImages[] = new int[]{R.drawable.banner3, R.drawable.banner1_1, R.drawable.banner1};
    public ProductDetail() { }
    public static ProductDetail newInstance(String param1, String param2) {
        ProductDetail fragment = new ProductDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        final TextView name = view.findViewById(R.id.pro_name_txt);
        final TextView price = view.findViewById(R.id.price_text);
        final TextView description = view.findViewById(R.id.description_text);
        final TextView descriptionClick = view.findViewById(R.id.description_title);
        proAdapter = new ProductDetailImagesListAdapter(productImages, this.getActivity());
        viewPager = view.findViewById(R.id.proDetViewPager);
        viewPager.setAdapter(proAdapter);
        ArrayList<Category> categories = new ArrayList<>();
        String productId = getArguments().getString("productID");
        ArrayList<Product> products = new ArrayList<>();


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            String unm = "admin";
            String pwd = "1234";
            String base = unm + ":" + pwd;
            String keyHeader = "stylestamp@123";

            //basic authentication encryption to BASE64
            String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

            Call<ProductResponse> callProducts = apiInterface.getProductById(authHeader, keyHeader, productId);
            Log.i("Calling:", "Products");
            callProducts.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.body() != null) {
                        Log.i("products to string:", response.toString());
                        ProductResponse productResponse = response.body();
                        Product product = productResponse.getProduct();
                        name.setText(product.getProductName());
                        price.setText(product.getPrice());
                        description.setText(product.getDescription());
                       /* descriptionClick.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                             description.setVisibility(View.VISIBLE);
                            }
                        });*/

                    } else {
                        Log.e("res-newarr-mes", response.message());
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Log.e("Products fail", "-------fail");
                    Log.e("res-newarr-body", t.getMessage());
                }
            });
            Button addToCart = view.findViewById(R.id.add_to_cart_button);
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sp = getActivity().getSharedPreferences("mp", 0);
                    String email=sp.getString("email",null);
                    if(email!=null)
                    {
                        signedIn=true;
                    }
                    else
                    {
                        signedIn=false;
                    }

                    if(!signedIn)
                    {
                        NotSignedInFragment notSignedIn = new NotSignedInFragment();
                        Bundle args = new Bundle();
                        args.putString("Fragment", "addToCart");
                        notSignedIn.setArguments(args);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, notSignedIn).addToBackStack(null).commit();

                    }
                    else {

                        //add to Cart
                    }

                }
            });


            RecyclerView recyclerViewRelatedProduct = (RecyclerView) view.findViewById(R.id.recyclerView_shop_relatedProducts);
            final RelatedProductsAdapter relatedProductsAdapter = new RelatedProductsAdapter(getActivity(), products);
            recyclerViewRelatedProduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            recyclerViewRelatedProduct.setAdapter(relatedProductsAdapter);

        return view;
    }
}
