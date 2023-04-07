package com.example.myapplication.logic;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    /**
     * Detemrine si la permission camera est déja accordé
      * @param context
     * @return
     */
    public boolean hasCameraPermission(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    public void askCameraPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
    }


    public void cameraPermissionFailedCloseActivity(int requestCode, String[] permissions, int[] grantResults, Activity currentActivity) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // L'utilisateur a accordé l'autorisation
            } else {
                // L'utilisateur a refusé l'autorisation, on ferme l'activité courrante
                currentActivity.finish();
            }
        }
    }
}
