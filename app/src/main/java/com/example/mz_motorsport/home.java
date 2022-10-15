package com.example.mz_motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class home extends Fragment {

    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imgcar = (ImageView) vista.findViewById(R.id.imgcar);
        imgcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CarDetails.class);
                startActivity(i);
            }
        });

        return vista;

    }

}