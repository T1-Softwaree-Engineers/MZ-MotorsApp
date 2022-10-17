package com.example.mz_motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class home extends Fragment {

    View vista;
    Button loguot;
    RelativeLayout post_container;
    String a;

    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_home, container, false);

        loguot = (Button)vista.findViewById(R.id.logout);
        post_container = (RelativeLayout) vista.findViewById(R.id.post_container);

        mAuth = FirebaseAuth.getInstance();


        post_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CarDetails.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), login2.class));
                getActivity().finish();

            }
        });

        return vista;

    }

}