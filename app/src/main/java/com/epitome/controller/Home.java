package com.epitome.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.epitome.R;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Shop shopFragment = new Shop();
    Profile profileFragment = new Profile();
    CartFragment cartFragment = new CartFragment();
    OrderHistoryFragment pd = new OrderHistoryFragment();

    private Toolbar toolbar;
    private static final String TAG = "HOME";
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        if (savedInstanceState == null)
            this.onNavigationItemSelected(bottomNavigationView.getMenu().getItem(0));
        bottomNavigationView.setSelectedItemId(R.id.nav_shop);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_shop:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, shopFragment).commit();
                return true;


            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return  true;

            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, cartFragment).commit();
                return  true;

        }
        return false;
    }
}
