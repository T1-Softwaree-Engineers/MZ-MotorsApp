package com.example.mz_motorsport;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Dialog d_contact;
    private List<MyPostElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    TextView vendido;
    Button markSold;



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
        CardView cardPost;

        ViewHolder(View itemView) {
            super(itemView);

            imgCar = itemView.findViewById(R.id.img_MyCarPostContainer);
            imgAutorizada = itemView.findViewById(R.id.Autorizada);
            delete = itemView.findViewById(R.id.DeleteMyPost);
            title = itemView.findViewById(R.id.MyTitle);
            price = itemView.findViewById(R.id.MyPrice);
            vendido = itemView.findViewById(R.id.vendido);
            cardPost = itemView.findViewById(R.id.MyFoto_titlePost);
            markSold = itemView.findViewById(R.id.MarcarVendida);
        }

        void bindData(final MyPostElement item) {
            imgCar.setImageResource(R.drawable.aveo);
            Picasso.get().load(item.getImgCar()+"/nomImg0.jpg").error(R.mipmap.ic_launcher_round).into(imgCar);
            title.setText(item.getTitle());
            NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
            price.setText(formatoImporte.format(item.getPrice()));
            if(item.getAutorizada() == 1){
                imgAutorizada.setImageResource(R.drawable.cheque);
            }
            if(item.getVendida() == 1){
                vendido.setText("SOLD");
                markSold.setEnabled(false);
            }
            d_contact = new Dialog(context);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "ID: "+item.getId(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, ""+item.getEmail_user(), Toast.LENGTH_SHORT).show();
                    openDialogDelete(item);
                }
            });

            markSold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openMarcarComoVendidaDialog(item);
                }
            });

            cardPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, CarDetails.class);
                    Bundle p = new Bundle();
                    p.putSerializable("MyPost", item);
                    i.putExtras(p);
                    i.putExtra("YourPost", true);
                    context.startActivity(i);
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
                deletePost("https://ochoarealestateservices.com/mzmotors/publicaciones.php?id="+item.getId()+"&email="+item.getEmail_user());
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


    private void openMarcarComoVendidaDialog(MyPostElement item){
        d_contact.setContentView(R.layout.delete_dialog);
        d_contact.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d_contact.show();

        TextView mensaje = d_contact.findViewById(R.id.txt1Mensaje);
        mensaje.setText("Do you want to Mark a SOLD this post");

        Button btn_confirm = d_contact.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markSoldPost("https://ochoarealestateservices.com/mzmotors/publicaciones.php?id="+item.getId());
                d_contact.dismiss();
                markSold.setEnabled(false);
                vendido.setText("SOLD");

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





    private void deletePost(String URL){
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


    private void markSoldPost(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()){
                    Toast.makeText(context, "Ocurrio un Error al Eliminar la Publicacion", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "Publicacion Vendida Exitosamente", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
