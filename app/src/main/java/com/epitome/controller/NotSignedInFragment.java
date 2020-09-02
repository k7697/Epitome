package com.epitome.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epitome.R;


public class NotSignedInFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public NotSignedInFragment() {
    }

    public static NotSignedInFragment newInstance(String param1, String param2) {
        NotSignedInFragment fragment = new NotSignedInFragment();
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
        View v = inflater.inflate(R.layout.fragment_not_signed_in, container, false);
        TextView description = v.findViewById(R.id.notsignedindescription);
        Button signIn = v.findViewById(R.id.notsignedin_signin_button);
        Button continueShopping = v.findViewById(R.id.notsignedin_continue_button);
        Button signUp = v.findViewById(R.id.notsignedin_signUp_button);
        String fragment = getArguments().getString("Fragment");
        if(fragment =="cart"){
            description.setText("You must be signed in to access cart.");
        }
        else if(fragment == "profile"){
            description.setText("You must be signed in to access your profile.");
        }
        else if(fragment == "addToCart"){
            description.setText("You must be signed in to add products cart.");
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Login.class);
                startActivity(in);

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SignUp.class);
                startActivity(in);
            }
        });
        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shop shop = new Shop();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, shop).addToBackStack(null).commit();

            }
        });

        return v;
    }
}
