package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.business.plant.PlantLightNeeds;
import com.example.myapplication.business.plant.PlantResistance;
import com.example.myapplication.business.plant.PlantSize;
import com.example.myapplication.storage.DBHandler;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);
        initLoaderAnimation();
        //generateInitialMock();

        CountDownTimer timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                handleRedirectLogin();
            }
        };
        timer.start();
    }

    public void handleRedirectLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        //Intent intent = new Intent(this, AddPlantActivity.class);
        startActivity(intent);
    }

    public void initLoaderAnimation() {
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(2000);
        ImageView loader = findViewById(R.id.loader);
        loader.setAnimation(rotate);
    }

    public void generateInitialMock() {
        DBHandler db = new DBHandler(this);

        Plant eucalyptus = new Plant("Eucalyptus");
        eucalyptus.setLightNeeds(PlantLightNeeds.MEDIUM.toString());
        eucalyptus.setSize(PlantSize.MEDIUM.toString());
        eucalyptus.setResistance(PlantResistance.STRONG.toString());
        db.addPlant(eucalyptus);

        Plant forcitia = new Plant("Forcitia");
        forcitia.setLightNeeds(PlantLightNeeds.LOW.toString());
        forcitia.setSize(PlantSize.LARGE.toString());
        forcitia.setResistance(PlantResistance.STRONG.toString());
        db.addPlant(forcitia);

        Plant trombitus = new Plant("Trombitus");
        trombitus.setLightNeeds(PlantLightNeeds.LOW.toString());
        trombitus.setSize(PlantSize.LARGE.toString());
        trombitus.setResistance(PlantResistance.WEAK.toString());
        db.addPlant(trombitus);

        Plant rectibitus = new Plant("Rectibitus");
        rectibitus.setLightNeeds(PlantLightNeeds.LOW.toString());
        rectibitus.setSize(PlantSize.TINY.toString());
        rectibitus.setResistance(PlantResistance.STRONG.toString());
        db.addPlant(rectibitus);

        Plant tessitorus = new Plant("Tessitorus");
        tessitorus.setLightNeeds(PlantLightNeeds.IMPORTANT.toString());
        tessitorus.setSize(PlantSize.LARGE.toString());
        tessitorus.setResistance(PlantResistance.WEAK.toString());
        db.addPlant(tessitorus);

        Plant analibitus = new Plant("Analibitus");
        analibitus.setLightNeeds(PlantLightNeeds.IMPORTANT.toString());
        analibitus.setSize(PlantSize.HUGE.toString());
        analibitus.setResistance(PlantResistance.WEAK.toString());
        db.addPlant(analibitus);
    }
}