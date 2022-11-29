package com.example.mz_motorsport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.controls.actions.FloatAction;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {

    TextView addphoto, uName, uEmail, uPhone;
    Button loguot;
    ImageView imgProfile;
    FloatingActionButton btnImgProfile;
    int PICK_IMAGE_REQUEST = 1;
    Uri imagenUri;
    List<Uri> listaImagenes = new ArrayList<>();
    String imgFinal = "";

    String URL_PHOTOS = "https://ochoarealestateservices.com/mzmotors/imgProfile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uName = (TextView)findViewById(R.id.name);
        uEmail = (TextView)findViewById(R.id.email);
        uPhone = (TextView)findViewById(R.id.phone);
        imgProfile = (ImageView)findViewById(R.id.profileImage);
        loguot = (Button)findViewById(R.id.btnlogout);
        btnImgProfile = (FloatingActionButton)findViewById(R.id.btnImgProfile);

        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        uName.setText(datosU.getString("nombre", "???Nombre???"));
        uEmail.setText(datosU.getString("email", "???Email???"));
        uPhone.setText(datosU.getString("phone", "???Contacto???"));
        String rutaImg = datosU.getString("img", "???img???");
        Picasso.get().load("https://ochoarealestateservices.com/mzmotors/"+rutaImg).error(R.mipmap.ic_launcher_round).into(imgProfile);

        btnImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
                /*for (int i = 0; i < listaImagenes.size(); i++){
                    try{
                        InputStream is = getContentResolver().openInputStream(listaImagenes.get(i));
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        imgFinal = convertirUtiToBase64(bitmap);
                        bitmap.recycle();

                    }catch (IOException e){

                    }
                }
                if(!imgFinal.isEmpty()){
                    Log.e("ERROR: ", imgFinal);
                    //enviarImagenes(imgFinal);
                }*/

            }

        });


        loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                datosU.edit().clear().commit();
                startActivity(new Intent(Profile.this, login2.class));
                finish();

            }
        });


    }


    public void enviarImagenes(String img) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PHOTOS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=datosU.edit();
                        editor.putString("img", response);
                        editor.commit();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, ""+error, Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();
                SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
                String uEmail = datosU.getString("email", "");

                //String imagen = getStringImagen(bitmap);
                params.put("Email", uEmail);
                params.put("imagen", img);
                //params.put("numImg", ""+cadena.size());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona imagen"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){

            Uri uri = data.getData();

            InputStream is = null;
            try {
                is = getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imgFinal = convertirUtiToBase64(bitmap);
            imgProfile.setImageURI(uri);
            enviarImagenes(imgFinal);

        }
    }

    public String convertirUtiToBase64(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        byte[] bytes = baos.toByteArray();
        String encode = Base64.encodeToString(bytes, Base64.DEFAULT);

        return encode;
    }

}