package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class sell_form extends AppCompatActivity {

    ImageView atras;
    EditText title, year, brand, model, location, price, description;
    RadioButton rbNew, rbUsed;
    Button btnSell;
    String txtTitle, txtYear, txtBrand, txtModel, txtLocation, txtPrice, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_form);

        atras = (ImageView)findViewById(R.id.flecha_atras);
        title = (EditText)findViewById(R.id.TitlePost);
        year = (EditText)findViewById(R.id.YearPost);
        brand = (EditText)findViewById(R.id.BrandPost);
        model = (EditText)findViewById(R.id.ModelPost);
        location = (EditText)findViewById(R.id.LocationPost);
        price = (EditText)findViewById(R.id.PricePost);
        title = (EditText)findViewById(R.id.TitlePost);
        description = (EditText)findViewById(R.id.descriptionPost);
        btnSell = (Button)findViewById(R.id.btnSell);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sell_form.this,navigation.class);
                startActivity(i);
                finish();
            }
        });


        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTitle = title.getText().toString();
                txtYear = year.getText().toString();
                txtBrand = brand.getText().toString();
                txtModel = model.getText().toString();
                txtLocation = location.getText().toString();
                txtPrice = price.getText().toString();
                txtDescription = description.getText().toString();

                if (txtTitle.isEmpty() || txtYear.isEmpty() || txtBrand.isEmpty() || txtModel.isEmpty() || txtLocation.isEmpty() ||txtPrice.isEmpty() || txtDescription.isEmpty()){
                    Toast.makeText(sell_form.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    createPost("http://192.168.50.166/APP/registerPost.php");
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(sell_form.this,navigation.class);
        startActivity(i);
        finish();
    }


    public void createPost(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){
                    startActivity(new Intent(login2.this, navigation.class));
                    finish();
                }else{
                    Toast.makeText(login2.this, "Usuario o Contraseña incorrectas", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(sell_form.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                String uEmail = datosU.getString("email", "");
                parametros.put("Email", uEmail);
                parametros.put("Title", txtTitle);
                parametros.put("Year", txtYear);
                parametros.put("Brand", txtBrand);
                parametros.put("Location", txtLocation);
                parametros.put("Price", txtPrice);
                parametros.put("Description", txtDescription);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}