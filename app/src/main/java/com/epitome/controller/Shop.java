package com.epitome.controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.R;
import com.epitome.adapter.CategoryListAdapter;
import com.epitome.adapter.NewArrivalsAdapter;
import com.epitome.model.Category;
import com.epitome.model.Product;
import com.epitome.response.CategoryResponse;
import com.epitome.response.ProductJsonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shop extends Fragment implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    Context context;

    RecyclerView recyclerView;
    CategoryListAdapter categoryListAdapter;
    ViewFlipper viewFlipper;
    SearchView searchView;

    List<Product> products = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    int images[] = {R.drawable.banner1, R.drawable.banner1_1, R.drawable.banner3};

    ImageView imgMore;

    public static Shop newInstance(String param1, String param2) {
        Shop fragment = new Shop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Shop() {
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
        final View v = inflater.inflate(R.layout.fragment_shop, container, false);
        viewFlipper = v.findViewById(R.id.viewPager);
        for (int image : images) {
            flipperImages(image);
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        imgMore = v.findViewById(R.id.imgMore);
        imgMore.setOnClickListener(this);
        final RecyclerView recyclerViewNewArrivals = (RecyclerView) v.findViewById(R.id.recyclerView_shop_newArrivals);

        TextView viewAll = v.findViewById(R.id.view_all_products);

        //for viewing all products
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductFragment productFragment = new ProductFragment();
                Bundle args = new Bundle();
                args.putString("CategoryID", "all");
                productFragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, productFragment).addToBackStack(null).commit();

            }
        });

        searchView = v.findViewById(R.id.searchView);

        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String unm = "admin";
        String pwd = "1234";
        String base = unm + ":" + pwd;
        final String keyHeader = "stylestamp@123";
        //basic authentication encryption to BASE64
        final String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);


        //______________getting categories_________________
        Call<CategoryResponse> call = apiInterface.getCategories(authHeader, keyHeader);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (!categories.isEmpty()) {
                        categories.clear();
                    }


                    categories = response.body().getCategories();

                    categoryListAdapter = new CategoryListAdapter(getActivity(), categories);
                    recyclerView.setAdapter(categoryListAdapter);
                    categoryListAdapter.notifyDataSetChanged();
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    initSearchView();

                } else {
                    Log.e("attaching", "nothing-shop-cat");
                    Log.e("shop-res", response.message());
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("category fail", t.toString());
            }
        });
        //______________getting new arrivals_________________
        Call<ProductJsonResponse> call3 = apiInterface.getAllProducts(authHeader, keyHeader);
        call3.enqueue(new Callback<ProductJsonResponse>() {
            @Override
            public void onResponse(Call<ProductJsonResponse> call3, Response<ProductJsonResponse> response3) {
                if (response3.isSuccessful() && response3.body() != null) {
                    if (!products.isEmpty()) {
                        products.clear();
                    }
                    products = response3.body().getProducts();
                    NewArrivalsAdapter newArrivalsAdapter = new NewArrivalsAdapter(getActivity(), products);
                    recyclerViewNewArrivals.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewNewArrivals.setAdapter(newArrivalsAdapter);


                } else {
                    Log.e("attaching--", "nothing-shop-new arrivals");
                    Log.e("shop-res-new arrivals--", response3.message());
                }
            }
            @Override
            public void onFailure(Call<ProductJsonResponse> call3, Throwable t) {

                Log.e("new arrivals fail--", t.toString());
            }
        });


        return v;

    }

    private void initSearchView() {
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                categoryListAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                categoryListAdapter.getFilter().filter(query);
                return false;
            }
        });
    }
    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMore:
                showMenu(v);
                break;
        }
    }

    private void showMenu(View v) {
        PopupMenu popup = new PopupMenu(getActivity(),v);
        popup.setOnMenuItemClickListener(this);// to implement on click event on items of menu
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.shop_option_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_return_policy:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new ReturnPolicyFragment()).commit();
                return true;
            case R.id.action_shipping:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new ShippingFragment()).commit();
                return true;
            case R.id.action_privacy:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new PrivacyPolicyFragment()).commit();
                return true;
            case R.id.action_terms_and_condition:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new TermsAndConditionFragment()).commit();
                return true;
            case R.id.action_contact_us:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new ContactUsFragment()).commit();
                return true;
            default:
                return false;
        }
    }
}
