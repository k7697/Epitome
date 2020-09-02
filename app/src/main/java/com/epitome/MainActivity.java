package com.epitome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import com.epitome.R;
import com.epitome.controller.Splash;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(getApplicationContext(), Splash.class);
        startActivity(i);

//
//
//        ApiClient client =  retrofit.create(ApiClient.class);

    }
}
