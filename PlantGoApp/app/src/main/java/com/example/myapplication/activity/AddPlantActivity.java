package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.storage.DBHandler;

public class AddPlantActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final int RESULT_OK = 1;
    Spinner lightNeedsSpinner;
    Spinner resistanceSpinner;
    Spinner sizeSpinner;
    private static final int BACKUP_TAKE_PHOTO = 1;
    private Button takePhotoButton;
    private ImageView photoImage;
    private String photoPath = null;

    EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        initActivity();
        setupComponents();
        applyArrayAdapters();
    }

    private void setupComponents() {
        // nom de la plante
        inputName = (EditText)findViewById(R.id.inputName);

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

    private void initActivity() {
        takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        photoImage = (ImageView) findViewById(R.id.photoImage);
        initTakePhoto();
    }

    private void initTakePhoto() {
        takePhotoButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTakePhoto();
            }
        });
    }

    private void handleTakePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            try {
                File photoFile = File.createTempFile("photo_"+time, ".jpg", photoDir);
                photoPath = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(AddPlantActivity.this, AddPlantActivity.this.getApplicationContext().getPackageName()+".provider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, BACKUP_TAKE_PHOTO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==BACKUP_TAKE_PHOTO && resultCode==RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath);
            photoImage.setImageBitmap(imageBitmap);
        }
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