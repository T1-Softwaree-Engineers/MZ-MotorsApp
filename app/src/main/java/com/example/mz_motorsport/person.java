package com.example.mz_motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

public class person extends Fragment {
    View vista;
    Button btnvender;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_person, container, false);
        btnvender = (Button) vista.findViewById(R.id.btnvender);
        // Inflate the layout for this fragment



        btnvender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),sell_form.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return vista;




    }



}