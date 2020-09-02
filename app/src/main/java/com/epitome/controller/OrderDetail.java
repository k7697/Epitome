package com.epitome.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epitome.R;
import com.epitome.adapter.OrderDetailListAdapter;
import com.epitome.model.CartProducts;
import com.epitome.model.Category;
import com.epitome.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Order order;
    OrderDetailListAdapter orderDetailListAdapter;
    List<CartProducts> products = new ArrayList<>();
    List<Category> categories = new ArrayList<>();



    public OrderDetail(Order _order) {
        this.order = _order;
    }

    public OrderDetail() {

    }

    public static OrderDetail newInstance(String param1, String param2) {
        OrderDetail fragment = new OrderDetail();
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


        products = order.getProducts();

//get List of products
        View v = inflater.inflate(R.layout.fragment_order_detail, container, false);
        orderDetailListAdapter = new OrderDetailListAdapter(getActivity(), products);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_orderDetails);
        recyclerView.setAdapter(orderDetailListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));*/
        return v;
    }
}


