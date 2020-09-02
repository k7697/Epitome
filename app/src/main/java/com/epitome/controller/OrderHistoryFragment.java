package com.epitome.controller;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.R;
import com.epitome.model.Order;
import com.epitome.response.OrderHistoryJsonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderHistoryFragment extends Fragment  {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Order> orders = new ArrayList<>();
    SharedPreferences sp;
    private String mParam1;
    private String mParam2;
    public OrderHistoryFragment() {

    }
    public static OrderHistoryFragment newInstance(String param1, String param2) {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
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

        final View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        sp = getActivity().getSharedPreferences("mp", 0);
        String uid = sp.getString("uid", null);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String unm = "admin";
        String pwd = "1234";
        String base = unm + ":" + pwd;
        String keyHeader = "stylestamp@123";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_orderHistory);

        final Call<OrderHistoryJsonResponse> call = apiInterface.getOrderHistory(authHeader, keyHeader, uid);
        call.enqueue(new Callback<OrderHistoryJsonResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryJsonResponse> call, Response<OrderHistoryJsonResponse> response) {
                Log.e("orderhistory-res", response.message());

                if (response.isSuccessful() && response.body().getOrders()!= null) {
                    if (!orders.isEmpty()) {
                        orders.clear();
                    }
                    orders = response.body().getOrders();

                   /* OrderListAdapter orderListAdapter = new OrderListAdapter(getActivity(), orders);
                    recyclerView.setAdapter(orderListAdapter);*/
                } else {
                    Log.e("orderHist-res", response.message());
                }

            }

            @Override
            public void onFailure(Call<OrderHistoryJsonResponse> call, Throwable t) {

                Log.e("fail", t.toString());
            }
        });





        /*
         *         tabLayout = view.findViewById(R.id.orderHistoryTabbedLayout);
         *//*activeTab = view.findViewById(R.id.active);
        previousTab = view.findViewById(R.id.previous);*//*
        viewPager = view.findViewById(R.id.orderHistoryContainer);
        pageAdapter = new OrderHistoryPagerAdapter(getFragmentManager(), tabLayout.getTabCount(), activeOrders, previousOrders);
        viewPager.setAdapter(pageAdapter);

        *//*viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
         *//*
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

   *//* private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private  List<String> fragmentList = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public  void addFragment(Fragment fragment , String title ){
            fragments.add(fragment);
            fragmentList.add(title);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


    }*/
        return view;
    }
}
