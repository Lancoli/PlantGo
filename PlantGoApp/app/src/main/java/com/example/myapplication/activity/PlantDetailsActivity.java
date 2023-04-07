package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.logic.plant.Plant;
import com.example.myapplication.storage.DBHandler;

public class PlantDetailsActivity extends AppCompatActivity {
    private Plant plant;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);
        initPlantDetails();
    }

    public void initPlantDetails() {
        db = new DBHandler(this);
        Intent intent = getIntent();

        int id = intent.getIntExtra("plant_id", 0);
        plant = db.getPlant(id);

        if (plant != null) {
            TextView viewName = findViewById(R.id.plant_details_name);
            TextView viewLightNeeds = findViewById(R.id.plant_details_lightNeeds);
            TextView viewResistance = findViewById(R.id.plant_details_resistance);
            TextView viewSize = findViewById(R.id.plant_details_size);
            ImageView viewImage = (ImageView) findViewById(R.id.plant_details_image);

            viewName.setText(plant.getName());
            viewLightNeeds.setText(plant.getLightNeeds());
            viewResistance.setText(plant.getResistance());
            viewSize.setText(plant.getSize());
            Bitmap imageBitmap = BitmapFactory.decodeFile(plant.getImageUrl());
            viewImage.setImageBitmap(imageBitmap);
        }
    }

    public void onClickGoBack(View view) {
        this.finish();
    }

    public void handleDeletePlant(View view) {
        db.deletePlant(plant.getId());
        onClickGoBack(view);
    }
}