package com.epitome.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.epitome.R;


public class Profile extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    Button myAccButton , orderHistoryButton;
    TextView helpAndInfo , changePassword, signOut;
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public Profile() {

    }


    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
       myAccButton= v.findViewById(R.id.btnMyAccount);
       orderHistoryButton = v.findViewById(R.id.btnorderhistory);
       helpAndInfo = v.findViewById(R.id.txtHelp);/*
       changePassword = v.findViewById(R.id.txtChangePassword);*/
       signOut = v.findViewById(R.id.txtlogout);
       boolean signedIn;


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
            args.putString("Fragment", "profile");
            notSignedIn.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, notSignedIn).addToBackStack(null).commit();

        } else {
       orderHistoryButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               AppCompatActivity activity = (AppCompatActivity) context;
               OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, orderHistoryFragment).addToBackStack(null).commit();
           }
       });
       signOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences settings = getActivity().getSharedPreferences("mp", Context.MODE_PRIVATE);
               settings.edit().clear().commit();
               Intent in = new Intent(getActivity(), Login.class);
               startActivity(in);

           }
       });

       helpAndInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new HelpAndInfoFragment()).commit();
           }
       });

       myAccButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new MyAccountFragment()).commit();
           }
       });
        }
        return v;
    }


}
