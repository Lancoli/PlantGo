package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDecouvrir(View view) {
        startActivity(new Intent(this,
                DecouvrirActivity.class));
    }

    public void onClickRandonner(View view) {
        startActivity(new Intent(this,
                RandonnerActivity.class));
    }
}