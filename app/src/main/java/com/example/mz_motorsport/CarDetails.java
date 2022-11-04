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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CarDetails extends AppCompatActivity {

    Button btn_interested;
    TextView seeAll;
    ImageView atras;

    Dialog d_contact;
    List<CarouselItem> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ImageCarousel carousel = findViewById(R.id.img_car);


        btn_interested = (Button)findViewById(R.id.btn_interested);
        atras = (ImageView)findViewById(R.id.flecha_atras);
        seeAll = (TextView)findViewById(R.id.seeAll);

        d_contact = new Dialog(this);
        // Image drawable with caption
        list.add(
                new CarouselItem(
                        R.drawable.car_1,
                        "Tesla model 3"


                )

        );
        carousel.setData(list);



        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarDetails.this,car_details_extra.class);
                startActivity(i);
                finish();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarDetails.this,navigation.class);
                startActivity(i);
                finish();
            }
        });

        btn_interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
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

        ImageView btn_close = d_contact.findViewById(R.id.imageViewClose);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_contact.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CarDetails.this, navigation.class));
        finish();
    }


}