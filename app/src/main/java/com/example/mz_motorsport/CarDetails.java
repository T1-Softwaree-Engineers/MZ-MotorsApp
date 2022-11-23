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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class CarDetails extends AppCompatActivity {

    Button btn_interested;
    TextView nameCar, priceCar, descriptionCar, locationCar, detailsCar, seeAll, txtFeature, txtFeature2;
    ImageView atras;

    RelativeLayout footer;
    String sellerName, sellerEmail, sellerPhone;
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
        txtFeature = (TextView)findViewById(R.id.txtFeature);
        txtFeature2 = (TextView)findViewById(R.id.txtFeature2);
        btn_interested = (Button)findViewById(R.id.btn_interested);
        footer = (RelativeLayout)findViewById(R.id.footer_bg);
        atras = (ImageView)findViewById(R.id.flecha_atras);
        seeAll = (TextView)findViewById(R.id.seeAll);

        boolean MyPost = getIntent().getBooleanExtra("YourPost", false);
        if(MyPost){
            btn_interested.setVisibility(View.GONE);

        }else{
            footer.setVisibility(View.GONE);
        }

        d_contact = new Dialog(this);
        // Image drawable with caption

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        //--------------------Set de la informacion--------------------
        Bundle objeto = getIntent().getExtras();
        MyPostElement MP = null;
        if (objeto != null){
            MP = (MyPostElement) objeto.getSerializable("MyPost");
            sellerEmail = MP.getEmail_user();
            //---------------Imagene dentro de la carpeta ---------------
            list.add(new CarouselItem(MP.getImgCar()+"/nomImg0.jpg"));
            list.add(new CarouselItem(MP.getImgCar()+"/nomImg1.jpg"));
            list.add(new CarouselItem(MP.getImgCar()+"/nomImg2.jpg"));
            list.add(new CarouselItem(MP.getImgCar()+"/nomImg3.jpg"));
            list.add(new CarouselItem(MP.getImgCar()+"/nomImg4.jpg"));

            //list.add(new CarouselItem(R.drawable.car_1, "Tesla model 3"));
            carousel.setData(list);
            //------------------------------------
            nameCar.setText(MP.getTitle());
            NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
            priceCar.setText(formatoImporte.format(MP.getPrice()));
            descriptionCar.setText(MP.getDescripcion());
            locationCar.setText(MP.getUbicacion());
            detailsCar.setText(MP.getMarca()+", "+MP.getModelo());

            String[] parts = MP.getFeatures().split(",");
            //----Acomodo de Fetures----
            txtFeature.setText(parts[0]);
            //txtFeature2.setText(parts[1]);
            getInfoSeller("https://ochoarealestateservices.com/mzmotors/users.php?email="+MP.getEmail_user());
        }

        MyPostElement finalMP = MP;
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarDetails.this, car_details_extra.class);
                Bundle p = new Bundle();
                p.putSerializable("MyElementsPost", finalMP);
                i.putExtras(p);
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

    private void openDialog() {
        d_contact.setContentView(R.layout.contact_dialog);
        d_contact.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d_contact.show();

        TextView sName, sEmail, sPhone;
        sName = d_contact.findViewById(R.id.txtSellerName);
        sEmail = d_contact.findViewById(R.id.txtSellerEmail);
        sPhone = d_contact.findViewById(R.id.txtSellerPhone);

        sName.setText(sellerName);
        sEmail.setText(sellerEmail);
        sPhone.setText(sellerPhone);

        Button btn_contact = d_contact.findViewById(R.id.btn_contact);
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri wha = Uri.parse("https://wa.me/52"+sellerPhone);
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

    private void getInfoSeller(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = response.getJSONObject(0);
                    sellerName = jsonObject.getString("nombre");
                    sellerPhone = jsonObject.getString("contacto");
                }catch (JSONException e){
                    Toast.makeText(CarDetails.this, "Vendedor no Encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CarDetails.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }


}