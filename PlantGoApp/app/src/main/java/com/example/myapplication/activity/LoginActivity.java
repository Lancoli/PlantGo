package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
        makeRequest();
    }

    public void makeRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly Error", error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}