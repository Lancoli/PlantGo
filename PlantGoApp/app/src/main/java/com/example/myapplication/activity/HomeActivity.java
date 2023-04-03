package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragments.NavbarFragment;

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