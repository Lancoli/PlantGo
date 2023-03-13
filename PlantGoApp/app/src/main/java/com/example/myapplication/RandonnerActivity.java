package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RandonnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randonner);

        TextView nomRando = (TextView) findViewById( R.id.nom_rando );
        nomRando.setText("Randonnée au fil de l'eau");

        ImageView img = (ImageView) findViewById( R.id.img_rando );
        img.setImageResource(R.drawable.map_image);
    }

    public void onClickRetour(View view) {
        this.finish();
    }
}