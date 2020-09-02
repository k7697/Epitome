package com.epitome.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.R;
import com.epitome.adapter.ProductListAdapter;
import com.epitome.model.Product;
import com.epitome.response.ProductJsonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    SearchView searchView;

    public ProductFragment() {
    }

    List<Product> products ;
    ProductJsonResponse productJsonResponse;

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View v = inflater.inflate(R.layout.fragment_product, container, false);

        searchView = v.findViewById(R.id.searchView);


        products = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.productRecyclerView);


        String unm = "admin";
        String pwd = "1234";
        String base = unm + ":" + pwd;
        String keyHeader = "stylestamp@123";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String categoryId = getArguments().getString("CategoryID");
        Log.e("catID:", categoryId);
        if(categoryId =="all") {

            //___________GETTING ALL PRODUCTS_________________

            Call<ProductJsonResponse> callProducts = apiInterface.getAllProducts(authHeader, keyHeader);
            Log.i("Calling:", "Products");
            callProducts.enqueue(new Callback<ProductJsonResponse>() {
                @Override
                public void onResponse(Call<ProductJsonResponse> call, Response<ProductJsonResponse> response) {
                    if (response.body() != null) {
                        Log.i("products to string:", response.toString());
                        ProductJsonResponse productJsonResponse = response.body();
                        products = productJsonResponse.getProducts();
                        final ProductListAdapter productListAdapter = new ProductListAdapter(getActivity(), products);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        recyclerView.setAdapter(productListAdapter);
                    } else {
                        Log.e("res-allpro-mes", response.message());
                    }
                }

                @Override
                public void onFailure(Call<ProductJsonResponse> call, Throwable t) {
                    Log.e("Products fail", "-------fail");
                    Log.e("res-allpro-body", t.getMessage());
                }
            });

        }else{

            //___________GETTING PRODUCTS_________________
            Call<List<Product>> call = apiInterface.getProductByCategoryId(authHeader, keyHeader, categoryId);

            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        products = response.body();
                        final ProductListAdapter productListAdapter = new ProductListAdapter(getActivity(), products);
                        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                // filter recycler view when query submitted
                                productListAdapter.getFilter().filter(query);
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String query) {
                                // filter recycler view when text is changed
                                productListAdapter.getFilter().filter(query);
                                return false;
                            }
                        });
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        recyclerView.setAdapter(productListAdapter);

                        // listening to search query text change
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                }
            });

        }
        searchView.setMaxWidth(Integer.MAX_VALUE);




        return v;
    }
}
