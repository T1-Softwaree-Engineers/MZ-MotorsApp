package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10;
    //CheckBox ChBoxS[] = {cb1, };
    Button btnSell;
    String txtTitle, txtCondition="", txtYear, txtBrand, txtModel, txtFeatures, txtLocation, txtPrice, txtDescription;

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
        description = (EditText)findViewById(R.id.descriptionPost);

        rbNew = (RadioButton)findViewById(R.id.rb_new);
        rbUsed = (RadioButton)findViewById(R.id.rb_used);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        cb3 = (CheckBox)findViewById(R.id.cb3);
        cb4 = (CheckBox)findViewById(R.id.cb4);
        cb5 = (CheckBox)findViewById(R.id.cb5);
        cb6 = (CheckBox)findViewById(R.id.cb6);
        cb7 = (CheckBox)findViewById(R.id.cb7);
        cb8 = (CheckBox)findViewById(R.id.cb8);
        cb9 = (CheckBox)findViewById(R.id.cb9);

        btnSell = (Button)findViewById(R.id.btnSell);


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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

                if (rbNew.isChecked()){
                    txtCondition = "0";
                }else if(rbUsed.isChecked()){
                    txtCondition = "1";
                }

                //------------------Features-------------------
                txtFeatures = "";

                if (cb1.isChecked()){
                    txtFeatures += cb1.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb2.isChecked()){
                    txtFeatures += cb2.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb3.isChecked()){
                    txtFeatures += cb3.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb4.isChecked()){
                    txtFeatures += cb4.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb5.isChecked()){
                    txtFeatures += cb5.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb6.isChecked()){
                    txtFeatures += cb6.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb7.isChecked()){
                    txtFeatures += cb7.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb8.isChecked()){
                    txtFeatures += cb8.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb9.isChecked()){
                    txtFeatures += cb9.getText().toString();
                    txtFeatures += ", ";
                }

                //------------------------------------------------

                if (txtTitle.isEmpty() || txtCondition.isEmpty() || txtYear.isEmpty() || txtBrand.isEmpty() || txtModel.isEmpty() || txtFeatures.isEmpty() || txtLocation.isEmpty() ||txtPrice.isEmpty() || txtDescription.isEmpty()){
                    Toast.makeText(sell_form.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(sell_form.this, "Lleno", Toast.LENGTH_SHORT).show();
                    Log.e("Features", txtFeatures);
                    createPost("http://192.168.50.166/publicaciones.php");
                }


            }
        });
    }


    public void createPost(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()){
                    Toast.makeText(sell_form.this, "Ocurrio un ErrorZZZ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(sell_form.this, ""+response, Toast.LENGTH_SHORT).show();
                    Log.e("Respuesta: ", response);
                }else {
                    Toast.makeText(sell_form.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    Toast.makeText(sell_form.this, ""+response, Toast.LENGTH_SHORT).show();
                    Log.e("Respuesta: ", response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(sell_form.this, ""+error, Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                String uEmail = datosU.getString("email", "");
                parametros.put("Email", uEmail);
                parametros.put("Title", txtTitle);
                parametros.put("Condition", txtCondition);
                parametros.put("Year", txtYear);
                parametros.put("Brand", txtBrand);
                parametros.put("Model", txtModel);
                parametros.put("Features", txtFeatures);
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