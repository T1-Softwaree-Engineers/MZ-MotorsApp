package com.example.mz_motorsport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.BitmapFactory;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class sell_form extends AppCompatActivity {

    RelativeLayout uploadImages;
    ImageView atras;
    GridView imgPubli;
    Uri imagenUri;
    EditText title, year, brand, model, location, price, description;
    RadioButton rbNew, rbUsed;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10;
    //CheckBox ChBoxS[] = {cb1, };
    Button btnSell;
    String txtTitle, txtCondition="", txtYear, txtBrand, txtModel, txtFeatures, txtLocation, txtPrice, txtDescription;
    Bitmap bitmap;
    Dialog d_contact;
    int PICK_IMAGE_REQUEST = 5;
    List<String> cadena = new ArrayList<>();

    List<Uri> listaImagenes = new ArrayList<>();
    List<String> listaBase64Imagenes = new ArrayList<>();
    GridViewAdapter baseAdapter;

    String URL_PHOTOS = "https://ochoarealestateservices.com/mzmotors/mkdir.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_form);

        uploadImages = (RelativeLayout)findViewById(R.id.container_btn_uploadImages);
        atras = (ImageView)findViewById(R.id.flecha_atras);
        imgPubli = (GridView) findViewById(R.id.imgPubli);
        title = (EditText)findViewById(R.id.TitlePost);
        title.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        year = (EditText)findViewById(R.id.YearPost);
        year.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        brand = (EditText)findViewById(R.id.BrandPost);
        brand.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        model = (EditText)findViewById(R.id.ModelPost);
        model.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        location = (EditText)findViewById(R.id.LocationPost);
        location.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
        price = (EditText)findViewById(R.id.PricePost);
        price.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        description = (EditText)findViewById(R.id.descriptionPost);

        rbNew = (RadioButton)findViewById(R.id.rb_new);
        rbUsed = (RadioButton)findViewById(R.id.rb_used);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        cb3 = (CheckBox)findViewById(R.id.cb3);
        cb4 = (CheckBox)findViewById(R.id.cb4);
        cb5 = (CheckBox)findViewById(R.id.cb5);
        cb6 = (CheckBox)findViewById(R.id.cb6);
        cb7 = (CheckBox)findViewById(R.id.cb7);
        cb8 = (CheckBox)findViewById(R.id.cb8);
        cb9 = (CheckBox)findViewById(R.id.cb9);

        btnSell = (Button)findViewById(R.id.btnSell);
        d_contact = new Dialog(this);


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });

        //------------Images-----------------

        uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


        //---------------------------------------
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTitle = title.getText().toString();
                txtYear = year.getText().toString();
                txtBrand = brand.getText().toString();
                txtModel = model.getText().toString();
                txtLocation = location.getText().toString();
                txtPrice = price.getText().toString();
                txtDescription = description.getText().toString();

                if (rbNew.isChecked()){
                    txtCondition = "0";
                }else if(rbUsed.isChecked()){
                    txtCondition = "1";
                }

                //------------------Features-------------------
                txtFeatures = "";

                if (cb1.isChecked()){
                    txtFeatures += cb1.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb2.isChecked()){
                    txtFeatures += cb2.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb3.isChecked()){
                    txtFeatures += cb3.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb4.isChecked()){
                    txtFeatures += cb4.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb5.isChecked()){
                    txtFeatures += cb5.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb6.isChecked()){
                    txtFeatures += cb6.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb7.isChecked()){
                    txtFeatures += cb7.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb8.isChecked()){
                    txtFeatures += cb8.getText().toString();
                    txtFeatures += ", ";
                }
                if (cb9.isChecked()){
                    txtFeatures += cb9.getText().toString();
                    txtFeatures += ", ";
                }

                //listaBase64Imagenes.clear();
                for (int i = 0; i < listaImagenes.size(); i++){
                    try{
                        InputStream is = getContentResolver().openInputStream(listaImagenes.get(i));
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        cadena.add(convertirUtiToBase64(bitmap));

                        bitmap.recycle();

                    }catch (IOException e){

                    }
                }

                //------------------------------------------------

                if (txtTitle.isEmpty() || txtCondition.isEmpty() || txtYear.isEmpty() || txtBrand.isEmpty() || txtModel.isEmpty() || txtFeatures.isEmpty() || txtLocation.isEmpty() ||txtPrice.isEmpty() || txtDescription.isEmpty()){
                    Toast.makeText(sell_form.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else if(cadena.size() < 5){
                    Toast.makeText(sell_form.this, ""+cadena.size(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(sell_form.this, "Ingrese al menos 5 Imagenes", Toast.LENGTH_SHORT).show();
                }else {
                    btnSell.setEnabled(false);
                    openDialogLoad();
                    createPost("https://ochoarealestateservices.com/mzmotors/publicaciones.php");
                    enviarImagenes(cadena);
                }


            }
        });
    }

    public void enviarImagenes(List<String> cadena) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PHOTOS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        d_contact.dismiss();
                        openDialogSucces();
                        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                d_contact.dismiss();
                                onBackPressed();
                            }
                        }.start();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                String uEmail = datosU.getString("email", "");

                //String imagen = getStringImagen(bitmap);
                params.put("Email", uEmail);
                for(int i=0; i<cadena.size(); i++){
                    params.put("imagen"+i, cadena.get(i));
                }
                //params.put("numImg", ""+cadena.size());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public String convertirUtiToBase64(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        byte[] bytes = baos.toByteArray();
        String encode = Base64.encodeToString(bytes, Base64.DEFAULT);

        return encode;
    }


    public void createPost(final String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()){
                    Toast.makeText(sell_form.this, "Ocurrio un ErrorZZZ", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(sell_form.this, ""+response, Toast.LENGTH_SHORT).show();
                    //Log.e("Respuesta: ", response);
                }else {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(sell_form.this, ""+error, Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                String uEmail = datosU.getString("email", "");
                //String imagen = getStringImagen(bitmap);
                parametros.put("Email", uEmail);
                parametros.put("Title", txtTitle);
                parametros.put("Condition", txtCondition);
                parametros.put("Year", txtYear);
                parametros.put("Brand", txtBrand);
                parametros.put("Model", txtModel);
                parametros.put("Features", txtFeatures);
                parametros.put("Location", txtLocation);
                parametros.put("Price", txtPrice);
                parametros.put("Description", txtDescription);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    /*
    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    } */


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ClipData clipData = data.getClipData();

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            //PARA CUANDO SE SELECCIONA UNA IMAGEN
            if(clipData == null){
                imagenUri = data.getData();
                listaImagenes.add(imagenUri);
            }
        }else {
            //PARA CUANDO SE SELECCIONAN VARIAS IMAGENES
            for (int i=0; i<clipData.getItemCount(); i++){
                listaImagenes.add(clipData.getItemAt(i).getUri());
            }
        }

        baseAdapter = new GridViewAdapter(sell_form.this, listaImagenes);
        imgPubli.setAdapter(baseAdapter);

    }

    private void openDialogLoad() {
        d_contact.setContentView(R.layout.load_dialog);
        d_contact.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d_contact.show();
        d_contact.setCanceledOnTouchOutside(false);
    }

    private void openDialogSucces() {
        d_contact.setContentView(R.layout.succes_dialog);
        d_contact.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d_contact.show();
        d_contact.setCanceledOnTouchOutside(false);

        TextView txtMensaje = d_contact.findViewById(R.id.txt1);
        txtMensaje.setText("Upload Success");
    }
}