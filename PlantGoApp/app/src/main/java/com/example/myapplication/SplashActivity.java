package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleRedirectLogin();
    }

    public void handleRedirectLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}