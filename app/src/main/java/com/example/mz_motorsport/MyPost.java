package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyPost extends AppCompatActivity {

    ImageView atras;
    List<MyPostElement> elements;
    String pId, pTitle, pPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        atras = (ImageView) findViewById(R.id.flecha_atras);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        init("http://192.168.1.70/publicaciones.php?email=oguzman3@ucol.mx");
    }

    public void init(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = response.getJSONObject(0);
                    pId = ""+jsonObject.getInt("id_post");
                    pTitle = jsonObject.getString("titulo");
                    pPrice = ""+jsonObject.getDouble("precio");


                }catch (JSONException e){
                    Toast.makeText(MyPost.this, "No se Encuentran los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyPost.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


        elements = new ArrayList<>();
        elements.add(new MyPostElement("sbe", pTitle, pPrice, "abcva", "23352"));
        elements.add(new MyPostElement("sbe", "Pontiac", "$ 135,324.00", "abcva", "23352"));
        elements.add(new MyPostElement("sbe", "Ferrari", "$ 1,000,000.00", "abcva", "23352"));
        elements.add(new MyPostElement("sbe", "Honda", "$ 241,965.00", "abcva", "23352"));
        elements.add(new MyPostElement("sbe", "Toyota", "$ 356,965.00", "abcva", "23352"));

        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.listRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }



}
