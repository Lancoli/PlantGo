package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavbarFragment navbarFragment = new NavbarFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navbar, navbarFragment)
                .commit();
    }
}