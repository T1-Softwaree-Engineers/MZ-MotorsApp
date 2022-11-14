package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    TextView addphoto, uName, uEmail, uPhone;
    Button loguot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uName = (TextView)findViewById(R.id.name);
        uEmail = (TextView)findViewById(R.id.email);
        uPhone = (TextView)findViewById(R.id.phone);
        loguot = (Button)findViewById(R.id.btnlogout);

        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        uName.setText(datosU.getString("nombre", "???Nombre???"));
        uEmail.setText(datosU.getString("email", "???Email???"));
        uPhone.setText(datosU.getString("phone", "???Contacto???"));


        loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                datosU.edit().clear().commit();
                startActivity(new Intent(Profile.this, login2.class));
                finish();

            }
        });


    }

}