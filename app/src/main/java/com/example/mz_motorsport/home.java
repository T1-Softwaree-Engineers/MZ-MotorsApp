package com.example.mz_motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class home extends Fragment {

    View vista;
    RelativeLayout post_container;

    List<CarouselItem> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_home, container, false);

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



        return vista;

    }

}