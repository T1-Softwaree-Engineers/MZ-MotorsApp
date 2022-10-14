package com.example.mz_motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;


public class home extends Fragment {

    View vista;
    RelativeLayout post;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_home, container, false);
        return vista;

    }

    public void clickPost(View view) {
        Intent i = new Intent(this, navigation.class);

    }
}