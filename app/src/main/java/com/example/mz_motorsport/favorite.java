package com.example.mz_motorsport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class favorite extends Fragment {

    View vista;
    ListAdapterFav adapter;
    List<MyPostElement> elements;
    RequestQueue requestQueue;
    ProgressBar pb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_favorite, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());
        pb = (ProgressBar) vista.findViewById(R.id.progress_bar);

        elements = new ArrayList<>();
        SharedPreferences datosU = getActivity().getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        String uEmail = datosU.getString("email", "???Email???");
        verPublicacionesFav("https://ochoarealestateservices.com/mzmotors/getAllFavPost.php?email="+uEmail);

        return vista;

    }


    public void verPublicacionesFav (String URL){
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
        //pb.setVisibility(View.GONE);
        ListAdapterFav listAdapterfav = new ListAdapterFav(elements, getActivity());
        RecyclerView recyclerView = vista.findViewById(R.id.listFavRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapterfav);
    }
}