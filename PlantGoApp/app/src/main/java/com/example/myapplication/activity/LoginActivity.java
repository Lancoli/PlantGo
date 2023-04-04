package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Permissions;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    TextView pseudonymeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView connectBtn = (TextView) findViewById(R.id.connectButton);
        pseudonymeEditText = findViewById(R.id.pseudo_input);
    }

    public void onClickSignUp(View view) {
        // on vérifie si le pseudo est rempli
        String pseudonyme = pseudonymeEditText.getText().toString();
        if (TextUtils.isEmpty(pseudonyme)) {
            Toast.makeText(this, "Le champ pseudonyme est obligatoire", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}