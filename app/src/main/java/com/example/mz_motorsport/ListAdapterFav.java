package com.example.mz_motorsport;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import java.text.NumberFormat;
import java.util.List;

public class ListAdapterFav extends RecyclerView.Adapter<ListAdapterFav.ViewHolder> {

    Dialog d_contact;
    private List<MyPostElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterFav(List<MyPostElement> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }
    @Override
    public ListAdapterFav.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.favorite_post_element, null);
        return new ListAdapterFav.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterFav.ViewHolder holder,final int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView carro;
        TextView txtPrice, txtTitle, deleteFav;
        RelativeLayout post_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            carro = itemView.findViewById(R.id.carro);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            post_container = itemView.findViewById(R.id.post_container);
            deleteFav = itemView.findViewById(R.id.deleteFav);
        }

        public void bindData(final MyPostElement item) {

            Picasso.get().load(item.getImgCar()+"/nomImg0.jpg").error(R.mipmap.ic_launcher_round).into(carro);
            NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
            txtPrice.setText(formatoImporte.format(item.getPrice()));
            txtTitle.setText(item.getTitle());
            d_contact = new Dialog(context);

            post_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, CarDetails.class);
                    Bundle p = new Bundle();
                    p.putSerializable("MyPost", item);
                    i.putExtras(p);
                    context.startActivity(i);
                }
            });

            deleteFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "ID: "+item.getId(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, ""+item.getEmail_user(), Toast.LENGTH_SHORT).show();
                    openDialogDelete(item);
                }
            });

        }

    }

    private void openDialogDelete(MyPostElement item) {

        d_contact.setContentView(R.layout.delete_dialog);
        d_contact.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d_contact.show();

        Button btn_confirm = d_contact.findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(item);
                notifyDataSetChanged();
                deleteFav("https://ochoarealestateservices.com/mzmotors/favorites.php?id="+item.getId()+"&email="+item.getEmail_user());
                d_contact.dismiss();
            }
        });

        Button btn_cancel = d_contact.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_contact.dismiss();
            }
        });
    }

    private void deleteFav(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()){
                    Toast.makeText(context, "Ocurrio un Error al Eliminar la Publicacion", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "Publicacion Eliminada Exitosamente", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());

            }
        });/*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("id", item.getId());
                return parametros;
            }
        };*/
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
