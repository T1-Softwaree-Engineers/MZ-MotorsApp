package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class car_details_extra extends AppCompatActivity {

    ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_extra);

        atras = (ImageView)findViewById(R.id.flecha_atras);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(car_details_extra.this, CarDetails.class);
                startActivity(i);
                finish();
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}