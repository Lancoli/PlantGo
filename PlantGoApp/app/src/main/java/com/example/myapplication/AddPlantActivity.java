package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        if(finalName != null) {
            Plant plant = new Plant(finalName);
            DBHandler db = new DBHandler(this);
            db.addPlant(plant);
            this.setResult(RESULT_OK, null);
            this.finish();
        }
    }
}