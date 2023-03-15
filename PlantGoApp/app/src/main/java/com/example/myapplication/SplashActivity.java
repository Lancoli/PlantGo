package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handleRedirectLogin();
    }

    public void handleRedirectLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}