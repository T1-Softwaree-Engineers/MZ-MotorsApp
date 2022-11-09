package com.example.mz_motorsport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<MyPostElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<MyPostElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.mypost_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }


    public void setItems(List<MyPostElement> items) {mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCar, imgVendida, imgAutorizada, delete;
        TextView title, price;

        ViewHolder(View itemView) {
            super(itemView);
            imgCar = itemView.findViewById(R.id.img_MyCarPostContainer);
            imgAutorizada = itemView.findViewById(R.id.Autorizada);
            imgVendida = itemView.findViewById(R.id.Vendida);
            delete = itemView.findViewById(R.id.DeleteMyPost);
            title = itemView.findViewById(R.id.MyTitle);
            price = itemView.findViewById(R.id.MyPrice);
        }

        void bindData(final MyPostElement item) {
            title.setText(item.getTitle());
            price.setText(item.getPrice());
        }
    }

}
