package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class PlantDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);
        initPlantDetails();
    }

    public void initPlantDetails() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String lightNeeds = intent.getStringExtra("lightNeeds");
        String resistance = intent.getStringExtra("resistance");
        String size = intent.getStringExtra("size");

        TextView viewName = findViewById(R.id.plant_details_name);
        TextView viewLightNeeds = findViewById(R.id.plant_details_lightNeeds);
        TextView viewResistance = findViewById(R.id.plant_details_resistance);
        TextView viewSize = findViewById(R.id.plant_details_size);

        viewName.setText(name);
        viewLightNeeds.setText(lightNeeds);
        viewResistance.setText(resistance);
        viewSize.setText(size);
    }

    public void onClickGoBack(View view) {
        this.finish();
    }
}