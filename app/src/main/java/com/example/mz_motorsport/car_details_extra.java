package com.example.mz_motorsport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class car_details_extra extends AppCompatActivity {

    ImageView atras;
    TextView txtMarca, txtModelo, txtYear, txtDescripcion, txtFeature1, txtFeature2;
    List<FeaturesElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_extra);

        atras = (ImageView)findViewById(R.id.flecha_atras);
        txtMarca = (TextView)findViewById(R.id.txtMarca);
        txtModelo = (TextView)findViewById(R.id.txtModelo);
        txtYear = (TextView)findViewById(R.id.txtAno);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcionExtra);
        //txtFeature1 = (TextView)findViewById(R.id.txtFeature1);
        //txtFeature2 = (TextView)findViewById(R.id.txtFeature2);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle objeto = getIntent().getExtras();
        MyPostElement MP = null;
        if (objeto != null) {
            MP = (MyPostElement) objeto.getSerializable("MyElementsPost");
            txtMarca.setText(MP.getMarca());
            txtModelo.setText(MP.getModelo());
            txtYear.setText(MP.getAño());
            txtDescripcion.setText(MP.getDescripcion());

            String[] parts = MP.getFeatures().split(",");
            int lengthFeatures = parts.length;
            elements = new ArrayList<>();
            for (int i=0; i < lengthFeatures; i++){
                elements.add(new FeaturesElement(parts[i]));
            }
            init();
            //txtFeature1.setText(parts[5]);
            //txtFeature2.setText(parts[0]);


        }

    }

    public void init () {
        ListAdapterFeatures listAdapterFeatures = new ListAdapterFeatures(elements, this);
        RecyclerView recyclerView = findViewById(R.id.featuresZZZ);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(listAdapterFeatures);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;

    }
}