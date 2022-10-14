package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class car_details_extra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_extra);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}