package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.storage.DBHandler;

public class AddPlantActivity extends AppCompatActivity {
    public static final int RESULT_OK = 1;

    EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        inputName = (EditText)findViewById(R.id.inputName);
        LinearLayout photoButton = (LinearLayout) this.findViewById(R.id.photoButton);
    }

    public void onClickValidatePlant(View view) {
        String finalName = inputName.getText().toString();
        if(finalName != null && finalName.isEmpty()) {
            Plant plant = new Plant(finalName);
            DBHandler db = new DBHandler(this);
            db.addPlant(plant);
            this.setResult(RESULT_OK, null);
            this.finish();
        } else {

        }
    }

    public void onClickGoBack(View view) {
        this.finish();
    }
}