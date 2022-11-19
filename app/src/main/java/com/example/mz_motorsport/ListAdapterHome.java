package com.example.mz_motorsport;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

    public void setItems(List<MyPostElement> items) {mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(MyPostElement myPostElement) {
        }
    }
}
