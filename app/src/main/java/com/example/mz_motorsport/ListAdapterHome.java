package com.example.mz_motorsport;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

<<<<<<< HEAD
import java.text.NumberFormat;
import java.util.ArrayList;
=======
>>>>>>> parent of 448fdfe... Finish CarDetails and some details
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListAdapterHome extends RecyclerView.Adapter<ListAdapterHome.ViewHolder> {

    Dialog d_contact;
    private List<MyPostElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterHome(List<MyPostElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    public void setfilteredlist(List<MyPostElement> filteredlist) {
        this.mData = filteredlist;
        notifyDataSetChanged();
    }

    @Override
    public ListAdapterHome.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_post_element, null);
        return new ListAdapterHome.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterHome.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }



    @Override
    public int getItemCount() {
       return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgcar;
        TextView titlecar,preciocar;
        RelativeLayout containerimg;

        public ViewHolder(View itemView) {
            super(itemView);

            imgcar = itemView.findViewById(R.id.imgcar);
            titlecar = itemView.findViewById(R.id.titlecar);
            preciocar = itemView.findViewById(R.id.preciocar);
            containerimg = itemView.findViewById(R.id.MyFoto_titlePost);
        }

        public void bindData(final MyPostElement item) {

                Picasso.get().load(item.getImgCar()+"/nomImg0.png").error(R.mipmap.ic_launcher_round).into(imgcar);
                titlecar.setText(item.getTitle());
                preciocar.setText("$ "+item.getPrice());
                d_contact = new Dialog(context);

        }

    }


}
