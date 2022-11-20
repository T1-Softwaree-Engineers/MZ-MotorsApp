package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class CarDetails extends AppCompatActivity {

    Button btn_interested;
    TextView nameCar, priceCar, descriptionCar, locationCar, detailsCar, seeAll;
    ImageView atras;

    Dialog d_contact;
    List<CarouselItem> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ImageCarousel carousel = findViewById(R.id.img_car);


        nameCar = (TextView)findViewById(R.id.name_car);
        priceCar = (TextView)findViewById(R.id.price_car);
        descriptionCar = (TextView)findViewById(R.id.description);
        locationCar = (TextView)findViewById(R.id.location);
        detailsCar = (TextView)findViewById(R.id.carDetails);
        btn_interested = (Button)findViewById(R.id.btn_interested);
        atras = (ImageView)findViewById(R.id.flecha_atras);
        seeAll = (TextView)findViewById(R.id.seeAll);

        d_contact = new Dialog(this);
        // Image drawable with caption




        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarDetails.this,car_details_extra.class);
                startActivity(i);

            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });



        //--------------------Set de la informacion--------------------

        Bundle objeto = getIntent().getExtras();
        MyPostElement MP = null;
        if (objeto != null){
            MP = (MyPostElement) objeto.getSerializable("MyPost");
            MP = (MyPostElement) objeto.getSerializable("HomePost");
            //---------------Imagen---------------
            list.add(new CarouselItem(MP.getImgCar()));

            //list.add(new CarouselItem(R.drawable.car_1, "Tesla model 3"));
            carousel.setData(list);
            //------------------------------------
            nameCar.setText(MP.getTitle());
            priceCar.setText("$ "+MP.getPrice());
            descriptionCar.setText(MP.getDescripcion());
            locationCar.setText(MP.getUbicacion());
            detailsCar.setText(MP.getMarca()+", "+MP.getModelo());
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
        return;
    }


}