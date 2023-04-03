package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.storage.DBHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPlantActivity extends AppCompatActivity {
    public static final int RESULT_OK = 1;

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