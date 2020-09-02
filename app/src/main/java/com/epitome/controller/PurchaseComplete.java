package com.epitome.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.epitome.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PurchaseComplete#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PurchaseComplete extends Fragment {

    private Button btnContinieshopping;

    Shop shopFragment = new Shop();

    public PurchaseComplete() {
        // Required empty public constructor
    }

    public static PurchaseComplete newInstance() {
        PurchaseComplete fragment = new PurchaseComplete();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_purchase_complete, container, false);

        btnContinieshopping = rootView.findViewById(R.id.btnContinieshopping);

        btnContinieshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, shopFragment).commit();
            }
        });

        return rootView;
    }
}
