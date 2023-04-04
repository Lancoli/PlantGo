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
import android.util.Log;
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

import com.example.myapplication.Permissions;
import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.business.utils.ToastMaker;
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

    Permissions permission = new Permissions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        initActivity();
    }

    private void initActivity() {
        takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        photoImage = (ImageView) findViewById(R.id.photoImage);
        initTakePhoto();

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

        applyArrayAdapters();
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
        //gestion de la permission camera
        boolean hasCameraPermission = permission.hasCameraPermission(this);

        if (!hasCameraPermission) {
            permission.askCameraPermissions(this);
        } else {
            openCameraIntent();
        }
    }

    private void openCameraIntent() {
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
        // cas de la prise de photo
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
        // récupération des valeur des champs
        boolean hasError = false;
        String finalName = inputName.getText().toString();
        String finalSize = sizeSpinner.getSelectedItem().toString();
        String finalLightNeeds = lightNeedsSpinner.getSelectedItem().toString();
        String finalResistance = resistanceSpinner.getSelectedItem().toString();


        boolean nameError = ToastMaker.EmptyToastValidator(this, finalName, "entrez un nom de plante");
        boolean sizeError = ToastMaker.EmptyToastValidator(this, finalSize, "entrez une taille");
        boolean resistanceError = ToastMaker.EmptyToastValidator(this, finalResistance, "entrez une resistance");
        boolean lightNeedsError = ToastMaker.EmptyToastValidator(this, finalLightNeeds, "entrez les besoin en lumière");


        if(nameError) hasError = true;
        if(sizeError) hasError = true;
        if(resistanceError) hasError = true;
        if(lightNeedsError) hasError = true;

        Log.d("formHasError", String.valueOf(hasError));

        if(!hasError) {
            Plant plant = new Plant(finalName, finalSize, finalResistance, finalLightNeeds);
            Log.e("> ------------------------------------------------ | postedPlant|", plant.toString());
            DBHandler db = new DBHandler(this);
            db.addPlant(plant);
            this.setResult(RESULT_OK, null);
            this.finish();
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleTakePhoto();
    }

}