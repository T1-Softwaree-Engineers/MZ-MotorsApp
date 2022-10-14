package com.example.mz_motorsport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class person extends Fragment {
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_person, container, false);
        // Inflate the layout for this fragment
        return vista;
    }
}