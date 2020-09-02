package com.epitome.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.epitome.api.ApiClient;
import com.epitome.R;
import com.epitome.model.User;

import retrofit2.Retrofit;

public class MyAccountFragment extends Fragment {

    private Button btnBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText editName;
    EditText editEmail;
    EditText editphonenumber;
    EditText editDeliveryInfo;
    EditText edittxtPaymentInfo;
    Retrofit retrofit= ApiClient.getClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_my_account, container, false);

        sharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editName = rootView.findViewById(R.id.editName);
        editEmail = rootView.findViewById(R.id.editEmail);
        editphonenumber = rootView.findViewById(R.id.editphonenumber);
        editDeliveryInfo = rootView.findViewById(R.id.editDeliveryInfo);
        edittxtPaymentInfo = rootView.findViewById(R.id.edittxtPaymentInfo);

        User user = new User("" + sharedPreferences.getString("userId",""),
                "",
                "" + sharedPreferences.getString("email",""),
                "" + sharedPreferences.getString("firstName",""),
                "" + sharedPreferences.getString("lastName",""),
                "" + sharedPreferences.getString("contact",""),
                "" + sharedPreferences.getString("dateOfBirth",""),
                "" + sharedPreferences.getString("gender",""));

        editName.setText(""+user.getFirstName() + " " + user.getLastName());
        editEmail.setText(""+user.getEmail());
        editphonenumber.setText(""+user.getContact());

        btnBack = rootView.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ConnectivityManager con = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netw = null;
                if (con != null) {
                    netw = con.getActiveNetworkInfo();
                }
                if (netw != null && netw.isConnected()) {
                    if(checkdata()) {
                        //calling service
                        //executeUpdateUser();
                    }
                } else {
                    Toast.makeText(getActivity(), "Internet Connection not available", Toast.LENGTH_LONG).show();
                }*/
                getActivity().getSupportFragmentManager().beginTransaction().remove(MyAccountFragment.this).commit();
            }
        });

        return rootView;
    }

    private boolean checkdata() {
        if(isValidEmail(editEmail.getText().toString().trim())){
            return true;
        }else{
            Toast.makeText(getActivity(), "email id is mandatory and must be in valid format", Toast.LENGTH_LONG).show();
        }
//        if(editName){
//            return true;
//        }else{
//            Toast.makeText(getApplicationContext(), "password is mandatory and must be atleast 8 character long", Toast.LENGTH_LONG).show();
//        }
        return false;
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    /*private void executeUpdateUser(){
        LoginService loginService=retrofit.create(LoginService.class);
        String unm="admin";
        String pwd="1234";
        String base=unm+":"+pwd;
        String keyHeader="stylestamp@123";
        String authHeader="Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);

        Call<JsonResponse> call=loginService.basicLogin(keyHeader,authHeader,email,password);

//                Call<User> call=loginService.basicLogin("jeelg46@gmail.com","12345678");
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                Log.e("login-res",response.message());

                if(response.body() != null) {
                    int status = response.body().getStatus();

//                    Log.e("status",String.valueOf(status));
//                    Log.e("name",response.body().user.getFirstName()+" "+response.body().user.getLastName());
//                    Log.e("email",response.body().user.getEmail());

                    if (status == 0) {
                        Toast.makeText(getActivity(), "Invalid Credential", Toast.LENGTH_SHORT).show();

                    } else if (status == 2) {
                        Toast.makeText(getActivity(), "Login Unsuccessful.", Toast.LENGTH_SHORT).show();

                    } else if (status == 1) {
                        Toast.makeText(getActivity(), "Login Successful.", Toast.LENGTH_SHORT).show();

                        User loginUser = response.body().user;

                        editor.putString("userId","" + loginUser.getUserId());
                        editor.putString("email",""+ loginUser.getEmail());
                        editor.putString("firstName",""+ loginUser.getFirstName());
                        editor.putString("lastName",""+ loginUser.getLastName());
                        editor.putString("contact",""+ loginUser.getContact());
                        editor.putString("dateOfBirth",""+ loginUser.getDateOfBirth());
                        editor.putString("gender",""+ loginUser.getGender());
                        editor.commit();
                    } else {
                        Toast.makeText(getActivity(), "Something Went Wrong.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }*/
}
