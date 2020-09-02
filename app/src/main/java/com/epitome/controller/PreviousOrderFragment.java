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


public class PreviousOrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";private String mParam1;
    private String mParam2;

    private List<Order> orders;
    OrderListAdapter orderListAdapter;
    public PreviousOrderFragment( List<Order> _orders) {
        this.orders = _orders;
    }

    public PreviousOrderFragment() {

    }

    public static PreviousOrderFragment newInstance(String param1, String param2) {
        PreviousOrderFragment fragment = new PreviousOrderFragment();
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
        orderListAdapter = new OrderListAdapter(getActivity(), orders);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_previous_order, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_previousOrders);
        recyclerView.setAdapter(orderListAdapter);
        return v;
    }
}
