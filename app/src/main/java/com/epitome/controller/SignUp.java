package com.epitome.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.epitome.api.ApiClient;
import com.epitome.api.LoginService;
import com.epitome.R;
import com.epitome.response.SignupJsonResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {
    EditText fname;
    EditText lname;
    EditText email;
    EditText contact_no;
    DatePicker dob;
    EditText password;
    Button signupSub;
    String f_name, l_name, e_mail, pass, contact_NO, Gender;
    RadioGroup radioSexGroup;
    int g;
    Retrofit retrofit = ApiClient.getClient();
    Date Dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupSub = findViewById(R.id.btnSignup);
        fname = findViewById(R.id.editFirstname);
        lname = findViewById(R.id.editLastname);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        dob = findViewById(R.id.datePicker);
        contact_no = findViewById(R.id.editContact);
        radioSexGroup = findViewById(R.id.radioGroup1);



       int selectedId = radioSexGroup.getCheckedRadioButtonId();
        final RadioButton selectedradio = findViewById(selectedId);
        signupSub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                f_name = fname.getText().toString();
                l_name = lname.getText().toString();
                e_mail = email.getText().toString();
                pass = password.getText().toString();
                contact_NO = contact_no.getText().toString();
              Gender = selectedradio.getText().toString();

                Log.e("gender", Gender);
                if (Gender == "Male") {
                    g = 0;
                } else {
                    g = 1;
                }

                String dateOfBirth = dob.getDayOfMonth()+"/"+ (dob.getMonth() + 1)+"/"+dob.getYear();
               /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(dob);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Dob = format.parse(dateString);
                    System.out.println(Dob);
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

                if (checkData()) {
                    executeSignupUser(f_name, l_name, contact_NO, e_mail, pass, g, dateOfBirth);

                }
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isGlobalPhoneNumber (String contact_NO){
        return true;
    }


    private boolean checkData() {


        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
        }

        if(isGlobalPhoneNumber(contact_NO)==false)
        {
            contact_no.setError("Enter Valid Contact Number!");
        }

        if (isEmpty(fname)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(lname)) {
            Toast t = Toast.makeText(this, "You must enter last name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(email)) {
            Toast t = Toast.makeText(this, "Email is required to register", Toast.LENGTH_SHORT);
            t.show();
        }
        if (TextUtils.isEmpty(pass) || pass.length() < 8) {
            password.setError("You must have 8 characters in your password");

        }
        return true;
    }



    private void executeSignupUser(String fnm, String lnm, String contact, String email, String password, int gender, String dob) {
        //String dob1 = "1-1-2020";
        Log.i("SignUp", "executeSignupUser: "+"first name: " + fnm);
        Log.i("SignUp", "executeSignupUser: "+"last name: " + lnm);
        Log.i("SignUp", "executeSignupUser: "+"contact: " + contact);
        Log.i("SignUp", "executeSignupUser: "+"email: " + email);
        Log.i("SignUp", "executeSignupUser: "+"password: " + password);
        Log.i("SignUp", "executeSignupUser: "+"gender: " + gender);
        Log.i("SignUp", "executeSignupUser: "+"dob: " + dob);
        LoginService loginService = retrofit.create(LoginService.class);
        String unm = "admin";
        String pwd = "1234";
        String base = unm + ":" + pwd;
        String keyHeader = "stylestamp@123";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        Call<SignupJsonResponse> call = loginService.basicSignup(keyHeader, authHeader, fnm, lnm, contact, email, password, gender, dob);

        //Toast.makeText(getApplicationContext(), authHeader, Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<SignupJsonResponse>() {
            @Override
            public void onResponse(Call<SignupJsonResponse> call, Response<SignupJsonResponse> response) {
                Log.e("login-res", response.message());
                Toast.makeText(SignUp.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                onBackPressed();
                if (response.body() != null) {
                    Log.e("msg", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SignupJsonResponse> call, Throwable t) {
                Log.e("login-res",t.toString());
                //Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}