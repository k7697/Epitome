package com.epitome.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epitome.R;
import com.epitome.adapter.OrderListAdapter;
import com.epitome.model.Order;

import java.util.List;


public class ActiveOrderFragment extends Fragment {
   private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Order> orders;
    private String mParam1;
    private String mParam2;
    OrderListAdapter orderListAdapter;



    public ActiveOrderFragment( List<Order> _orders) {
        this.orders = _orders;
    }

    public ActiveOrderFragment() {

    }


    public static ActiveOrderFragment newInstance(String param1, String param2) {
        ActiveOrderFragment fragment = new ActiveOrderFragment();
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
        View v = inflater.inflate(R.layout.fragment_active_order, container, false);
        orderListAdapter = new OrderListAdapter(getActivity(), orders);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_activeOrders);
        recyclerView.setAdapter(orderListAdapter);
        return v;
    }
}
