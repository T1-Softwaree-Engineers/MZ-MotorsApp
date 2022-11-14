package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    String globalImg = "http://192.168.50.166/uploads/thumb-1920-1083140.jpg";

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        requestQueue = Volley.newRequestQueue(this);
        atras = (ImageView) findViewById(R.id.flecha_atras);
        //imgP = (ImageView)findViewById(R.id.imgPrueba);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        String uEmail = datosU.getString("email", "???Email???");
        Toast.makeText(this, ""+uEmail, Toast.LENGTH_SHORT).show();
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
                        String pTitle = jsonObject.getString("titulo");
                        String pPrice = "" + jsonObject.getDouble("precio");
                        String pFoto = jsonObject.getString("photos");

                        elements.add(new MyPostElement(pFoto, pTitle, pPrice, "abcva", "23352"));
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
        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.listRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

}
