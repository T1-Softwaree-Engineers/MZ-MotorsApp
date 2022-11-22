package com.example.mz_motorsport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterFeatures extends RecyclerView.Adapter<ListAdapterFeatures.ViewHolder>{
    private List<FeaturesElement> mData;
    private LayoutInflater mInflater;
    private Context context;



    public ListAdapterFeatures(List<FeaturesElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {return mData.size(); }

    @Override
    public ListAdapterFeatures.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.feature_element, null);
        return new ListAdapterFeatures.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterFeatures.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));

    }


    public void setItems(List<FeaturesElement> items) {mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;


        ViewHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.txtFeatureZZZ);

        }

        void bindData(final FeaturesElement item) {
            text.setText(item.getText());
        }
    }
}
