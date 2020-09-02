package com.epitome.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.epitome.R;

public class Splash extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    boolean signedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences("mp", 0);
        editor = sp.edit();
        String email=sp.getString("email",null);
        if(email!=null)
        {
            signedIn=true;
        }
        else
        {
            signedIn=false;
        }
        Thread d=new Thread()
        {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(2000);
                }
                catch(Exception e){

                }
                finally {

                    if(signedIn)
                    {
                        Log.e("signedin ", "yeah");
                        Intent in = new Intent(Splash.this, Home.class);
                        startActivity(in);
                    }
                    else
                    {
                        Log.e("signedin ", "no");/*
                        Intent in = new Intent(Splash.this, Login.class);*/
                        Intent in = new Intent(Splash.this, Login.class);
                        startActivity(in);

                    }
                }

            }
        };
        d.start();
    }
}