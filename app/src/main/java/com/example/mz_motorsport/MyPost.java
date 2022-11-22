package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyPost extends AppCompatActivity {

    ImageView atras, imgP;
    List<MyPostElement> elements;
    ProgressBar pb;
    int numFotos=0;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        requestQueue = Volley.newRequestQueue(this);
        atras = (ImageView) findViewById(R.id.flecha_atras);
        pb = (ProgressBar)findViewById(R.id.progress_bar);
        //imgP = (ImageView)findViewById(R.id.imgPrueba);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        String uEmail = datosU.getString("email", "???Email???");
        verPublicaciones("https://ochoarealestateservices.com/mzmotors/publicaciones.php?email="+uEmail);
    }

    public void verPublicaciones(String URL) {
        elements = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                try {
                    for (int i=0; i < response.length(); i++){
                        jsonObject = response.getJSONObject(i);
                        String pId = "" + jsonObject.getInt("id_post");
                        String pEUser = jsonObject.getString("email_user");
                        String pTitle = jsonObject.getString("titulo");
                        double pPrice = jsonObject.getDouble("precio");
                        String pFoto = jsonObject.getString("photos");
                        String pMarca = jsonObject.getString("marca");
                        String pModelo = jsonObject.getString("modelo");
                        String pAño = jsonObject.getString("año");
                        Double pPrecio = jsonObject.getDouble("precio");
                        String pUbicacion = jsonObject.getString("ubicacion");
                        String pFeatures = jsonObject.getString("features");
                        int pCondicion = jsonObject.getInt("condicion");
                        String pDescripcion = jsonObject.getString("descripcion");
                        int pAutorizada = jsonObject.getInt("autorizada");
                        int pVendida = jsonObject.getInt("vendida");

                        elements.add(new MyPostElement(pId, pEUser, pFoto, pTitle, pMarca, pModelo, pAño, pPrice, pUbicacion, pFeatures, pCondicion, pDescripcion, pAutorizada, pVendida));
                    }
                    init();
                } catch (JSONException e) {
                    Toast.makeText(MyPost.this, "No se Encuentran los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyPost.this, "Error: ", Toast.LENGTH_SHORT).show();
                Toast.makeText(MyPost.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    public void init(){
        //elements.add(new MyPostElement("sbe", "Pontiac", "$ 135,324.00", "abcva", "23352"));
        pb.setVisibility(View.GONE);
        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.listRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }


}
