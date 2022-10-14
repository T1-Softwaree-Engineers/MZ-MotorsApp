package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CarDetails extends AppCompatActivity {

    Button loguot,btn_interested;
    TextView seeAll;

    Dialog d_contact;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        loguot = (Button)findViewById(R.id.logout);
        btn_interested = (Button)findViewById(R.id.btn_interested);
        seeAll = (TextView)findViewById(R.id.seeAll);

        d_contact = new Dialog(this);

        mAuth = FirebaseAuth.getInstance();

        loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(CarDetails.this, login2.class));
                finish();
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarDetails.this,car_details_extra.class);
                startActivity(i);
            }
        });

        btn_interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(CarDetails.this, login2.class));
        }
    }

    private void openDialog() {
        d_contact.setContentView(R.layout.contact_dialog);
        d_contact.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d_contact.show();

        Button btn_contact = d_contact.findViewById(R.id.btn_contact);
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri wha = Uri.parse("https://wa.me/523141027774");
                Intent i = new Intent(Intent.ACTION_VIEW, wha);
                startActivity(i);
            }
        });
    }

}