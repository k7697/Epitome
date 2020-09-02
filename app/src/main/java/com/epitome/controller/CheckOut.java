package com.epitome.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.epitome.R;
import com.epitome.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckOut#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOut extends Fragment {

    private Button btnPurchase;

    PurchaseComplete purchaseComplete = new PurchaseComplete();

    private EditText editName, editEmail, editphonenumber, editDeliveryInfo, edittxtPaymentInfo, editAddress;
    private CheckBox chkDeliveryMethod;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public CheckOut() {
        // Required empty public constructor
    }

    public static CheckOut newInstance() {
        CheckOut fragment = new CheckOut();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);

        sharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        User user = new User("" + sharedPreferences.getString("userId",""),
                "",
                "" + sharedPreferences.getString("email",""),
                "" + sharedPreferences.getString("firstName",""),
                "" + sharedPreferences.getString("lastName",""),
                "" + sharedPreferences.getString("contact",""),
                "" + sharedPreferences.getString("dateOfBirth",""),
                "" + sharedPreferences.getString("gender",""));

        btnPurchase = rootView.findViewById(R.id.btnPurchase);

        editName = rootView.findViewById(R.id.editName);
        editEmail = rootView.findViewById(R.id.editEmail);
        editphonenumber = rootView.findViewById(R.id.editphonenumber);
        editAddress = rootView.findViewById(R.id.editAddress);
        editDeliveryInfo = rootView.findViewById(R.id.editDeliveryInfo);
        edittxtPaymentInfo = rootView.findViewById(R.id.edittxtPaymentInfo);

        chkDeliveryMethod = rootView.findViewById(R.id.chkDeliveryMethod);

        editName.setText("" + user.getFirstName() + " " + user.getLastName());
        editEmail.setText("" + user.getEmail());
        editphonenumber.setText("" + user.getContact());
        editAddress.setText("123 Rue Thanksville");
        editDeliveryInfo.setText("Online");
        edittxtPaymentInfo.setText("PayPal");

        chkDeliveryMethod.setChecked(true);

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, purchaseComplete).commit();
            }
        });

        return rootView;
    }
}
