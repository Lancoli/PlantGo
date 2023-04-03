package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.business.plant.PlantLightNeeds;
import com.example.myapplication.business.plant.PlantResistance;
import com.example.myapplication.business.plant.PlantSize;
import com.example.myapplication.storage.DBHandler;

public class AddPlantActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final int RESULT_OK = 1;
    Spinner lightNeedsSpinner;
    Spinner resistanceSpinner;
    Spinner sizeSpinner;
    EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        setupComponents();
        applyArrayAdapters();
    }

    private void setupComponents() {
        // nom de la plante
        inputName = (EditText)findViewById(R.id.inputName);
        LinearLayout photoButton = (LinearLayout) this.findViewById(R.id.photoButton);

        //recup besoin en lumière de la plante
        lightNeedsSpinner = (Spinner) findViewById(R.id.spinnerLightNeeds);
        lightNeedsSpinner.setOnItemSelectedListener(this);

        //recup resistance de la plante
        resistanceSpinner = (Spinner) findViewById(R.id.spinnerResistance);
        resistanceSpinner.setOnItemSelectedListener(this);

        //recup taille de la plante
        sizeSpinner = (Spinner) findViewById(R.id.spinnerSize);
        sizeSpinner.setOnItemSelectedListener(this);
    }

    /**
     * Cette méthode prepare les arrayAdapter pour mapper les options des spinner (box de selection)
     */
    public void applyArrayAdapters() {
        //Besoin en lumière
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Plant.getLightNeedsOptions());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lightNeedsSpinner.setAdapter(aa);

        //Resistance de la plante
        ArrayAdapter adapterResitance = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Plant.getResistanceOptions());
        adapterResitance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resistanceSpinner.setAdapter(adapterResitance);

        //Resistance de la plante
        ArrayAdapter adapterSize = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Plant.getSizeOptions());
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapterSize);
    }


    public void onClickValidatePlant(View view) {
        String finalName = inputName.getText().toString();

        if(TextUtils.isEmpty(finalName)) {
            Toast.makeText(this, "entrez un nom de plante", Toast.LENGTH_SHORT).show();
            return;
        }

        Plant plant = new Plant(finalName);
        DBHandler db = new DBHandler(this);
        db.addPlant(plant);
        this.setResult(RESULT_OK, null);
        this.finish();
    }

    private boolean checkFields() {
        boolean isValid = true;

        return isValid;
    }

    public void onClickGoBack(View view) {
        this.finish();
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getApplicationContext(),lightNeedsOptions[i] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}