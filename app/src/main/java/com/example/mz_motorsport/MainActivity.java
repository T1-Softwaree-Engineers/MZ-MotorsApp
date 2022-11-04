package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation topanim;
    ImageView logosplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);




        //Animacion para la splash screen
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        //hooks
        logosplash = findViewById(R.id.mzmotor);

        logosplash.setAnimation(topanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                boolean session = datosU.getBoolean("session",false);
                if(session){
                    startActivity(new Intent(MainActivity.this, navigation.class));
                    finish();
                }else{
                    startActivity(new Intent(MainActivity.this, login2.class));
                    finish();
                }
            }
        },SPLASH_SCREEN);

    }
}