package com.example.mz_motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class person extends Fragment {

    View vista;
    CardView btnvender, btnMypost, btnProfile;
    TextView terms_conditions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_person, container, false);
        btnvender = (CardView) vista.findViewById(R.id.btnvender);
        btnMypost = (CardView) vista.findViewById(R.id.btnMyPost);
        btnProfile = (CardView) vista.findViewById(R.id.btnProfile);

        terms_conditions = (TextView) vista.findViewById(R.id.terms_conditions);


        // Inflate the layout for this fragment



        btnvender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),sell_form.class);
                startActivity(i);

            }
        });

        btnMypost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MyPost.class);
                startActivity(i);

            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Profile.class);
                startActivity(i);
            }
        });

        terms_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), politicas.class);
                i.putExtra("pdf_url", "https://ochoarealestateservices.com/mzmotors/Politicas%20de%20Privacidad%20MZMOTORS%20APP.pdf");
                startActivity(i);
            }
        });


        return vista;


    }



}