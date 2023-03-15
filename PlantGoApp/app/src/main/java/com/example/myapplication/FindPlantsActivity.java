package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class FindPlantsActivity extends AppCompatActivity {
    private CategoriesFragment categoriesFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_plants);

        NavbarFragment navbarFragment = new NavbarFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navbar, navbarFragment)
                .commit();

        categoriesFragment = new CategoriesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.categories, categoriesFragment)
                .commit();
    }

    public void onClickGoBack(View view) {
        this.finish();
    }
}