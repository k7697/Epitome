package com.epitome.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epitome.R;

public class ForgetPassword extends AppCompatActivity {
    EditText et_Email;
    Button cont;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        cont = findViewById(R.id.btnContinue);
        et_Email = findViewById(R.id.editEmail);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = et_Email.getText().toString();
                if (checkData()) {
                    Intent in = new Intent(ForgetPassword.this, Login.class);
                    startActivity(in);
                }
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean checkData() {

        if (isEmail(et_Email) == false) {
            et_Email.setError("Enter valid email!");
        }
        return true;
    }
}