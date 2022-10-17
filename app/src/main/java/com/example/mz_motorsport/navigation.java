package com.example.mz_motorsport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class navigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    home home = new home();
    person person = new person();
    favorite favorite = new favorite();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        bottomNavigationView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,home).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,home).commit();
                        return true;

                    case R.id.person:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,person).commit();
                        return true;

                    case R.id.favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,favorite).commit();
                        return true;
                }
                return false;
            }
        });
    }

}