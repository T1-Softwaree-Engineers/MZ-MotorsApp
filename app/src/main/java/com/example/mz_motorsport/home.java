package com.example.mz_motorsport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;


public class home extends Fragment {

    View vista;
    RelativeLayout post_container;

    List<CarouselItem> list = new ArrayList<>();
    List<MyPostElement> elements;
    ProgressBar pb;

    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_home, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());
        pb = (ProgressBar) vista.findViewById(R.id.progress_bar);

        ImageCarousel carousel = vista.findViewById(R.id.carousel);

        list.add(
                new CarouselItem(
                        R.drawable.swift,
                        "Susuki Swift"
                )
        );
        list.add(
                new CarouselItem(
                        R.drawable.aveo,
                        "Chevrolet Aveo"
                )
        );
        list.add(
                new CarouselItem(
                        R.drawable.bmw,
                        "BMW"
                )
        );
        list.add(
                new CarouselItem(
                        R.drawable.mercedes,
                        "Mercedez Benz"
                )
        );
        list.add(
                new CarouselItem(
                        R.drawable.corolla,
                        "Toyota Corolla"
                )
        );

        carousel.setData(list);

        /*
        post_container = (RelativeLayout) vista.findViewById(R.id.post_container);



        post_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CarDetails.class);
                startActivity(i);
            }
        }); */
        verPublicacionesHome("https://ochoarealestateservices.com/mzmotors/getAllAutorizedPost.php");

        return vista;

    }

    public void verPublicacionesHome (String URL){
        elements = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                try {
                    for (int i = 0; i < response.length(); i++) {
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
                    Toast.makeText(getActivity(), "No se Encuentran los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: ", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    public void init () {
        //elements.add(new MyPostElement("sbe", "Pontiac", "$ 135,324.00", "abcva", "23352"));
        pb.setVisibility(View.GONE);
        ListAdapterHome listAdapterHome = new ListAdapterHome(elements, getActivity());
        RecyclerView recyclerView = vista.findViewById(R.id.listHomeRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(listAdapterHome);
    }

}