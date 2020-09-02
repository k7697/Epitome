package com.epitome.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.R;
import com.epitome.adapter.CartListAdapter;
import com.epitome.model.CartProducts;
import com.epitome.response.CartJasonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    boolean signedIn;
    private ArrayList<CartProducts> cartProducts = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    CartListAdapter cartListAdapter;
Context context;

    CheckOut checkOutFragment = new CheckOut();

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        sp = getActivity().getSharedPreferences("mp", 0);
        String email = sp.getString("email", null);

        if (email != null) {
            signedIn = true;
        } else {
            signedIn = false;
        }

        if (!signedIn) {
            NotSignedInFragment notSignedIn = new NotSignedInFragment();
            Bundle args = new Bundle();
            args.putString("Fragment", "cart");
            notSignedIn.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, notSignedIn).addToBackStack(null).commit();

        } else {
            final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_cart);
            editor = sp.edit();
            String uid = sp.getString("uid", null);

            final TextView orderValue = rootView.findViewById(R.id.orderValueTextField);
            TextView couponDiscount = rootView.findViewById(R.id.couponDiscountTextView);
            final TextView shipping = rootView.findViewById(R.id.shippingPriceTextView);
            final TextView total = rootView.findViewById(R.id.orderTotalTextView);


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            String unm = "admin";
            String pwd = "1234";
            String base = unm + ":" + pwd;
            String keyHeader = "stylestamp@123";
            String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
            Call<CartJasonResponse> call;

            call = apiInterface.getCart(authHeader, keyHeader, uid);
            call.enqueue(new Callback<CartJasonResponse>() {
                @Override
                public void onResponse(Call<CartJasonResponse> call, Response<CartJasonResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (!cartProducts.isEmpty()) {
                            cartProducts.clear();
                        }

                        Log.e("attaching", "cartListAdapter");
                        cartProducts = response.body().getCart().getCartProducts();
                        Log.e("cart-res-message", response.body().getMessage());

                        cartListAdapter = new CartListAdapter(getActivity(), cartProducts);
                        recyclerView.setAdapter(cartListAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


                        double totalSP = Double.parseDouble(sp.getString("total", "0"));
                        if(totalSP!=0  ) {
                            Log.e("tot", String.valueOf(totalSP));
                            double shippingCost = 8;
                            double grandTotal = totalSP + shippingCost;
                            orderValue.setText("$" + String.valueOf(totalSP));
                            shipping.setText("$" + String.valueOf(shippingCost));
                            total.setText("$" + String.valueOf(grandTotal));
                        }
                        else {
                            AppCompatActivity activity = (AppCompatActivity) context;
                            CartFragment cartFragment = new CartFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,cartFragment).addToBackStack(null).commit();


                        }


                    } else {

                        Log.e("attaching", "nothing-cart");
                        Log.e("res-body", response.message());
                        Toast.makeText(getActivity(), "No results", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CartJasonResponse> call, Throwable t) {
                    Log.e("problem: ", t.getMessage().toString());
                    Log.e("code: ", t.getStackTrace().toString());
                }
            });


            Button btnCheckout;
            btnCheckout = rootView.findViewById(R.id.btnCheckout);
            btnCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, checkOutFragment).commit();
                }
            });
        }
        return rootView;
    }


}
