package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragments.CategoriesFragment;

public class FindPlantsActivity extends AppCompatActivity {
    private CategoriesFragment categoriesFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_plants);

        categoriesFragment = new CategoriesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.categories, categoriesFragment)
                .commit();
    }

    public void onClickGoBack(View view) {
        this.finish();
    }
}