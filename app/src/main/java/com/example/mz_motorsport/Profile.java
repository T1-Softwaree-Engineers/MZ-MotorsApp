package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView addphoto;
    Button loguot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        loguot = (Button)findViewById(R.id.btnlogout);


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