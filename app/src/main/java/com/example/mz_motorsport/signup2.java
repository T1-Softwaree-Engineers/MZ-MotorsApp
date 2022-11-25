package com.example.mz_motorsport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class signup2 extends AppCompatActivity {

    TextView login;
    EditText et_name, et_email, et_phone, et_password;
    Button btn_Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = (TextView)findViewById(R.id.login4);
        et_name = (EditText)findViewById(R.id.et_name);
        et_email = (EditText)findViewById(R.id.et_email);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_Signup = (Button)findViewById(R.id.btnsignup);

        //Boton Registrarse
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                if (et_name.length() == 0){
                    Toast.makeText(signup2.this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show();
                }else if(et_email.length() == 0){
                    Toast.makeText(signup2.this, "Ingrese un Correo", Toast.LENGTH_SHORT).show();
                }else if(et_phone.length() == 0){
                    Toast.makeText(signup2.this, "Ingrese un Telefono", Toast.LENGTH_SHORT).show();
                }else if(et_password.length() == 0){
                    Toast.makeText(signup2.this, "Ingrese una Contraseña", Toast.LENGTH_SHORT).show();
                }else if (et_password.length() > 1 && et_password.length() < 6){
                    Toast.makeText(signup2.this, "Su contraseña debe de tener mas de 6 caracteres", Toast.LENGTH_SHORT).show();
                }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
                    et_email.setError("please enter a valid email");
                }else if (et_phone.length() > 1 && et_phone.length() < 11){
                    et_email.setError("please enter a valid phone");
                }else {
                    registrarUser("https://ochoarealestateservices.com/mzmotors/users.php");
                }

            }
        });

        //Boton Ir a Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup2.this, login2.class));
                finish();
            }
        });
    }

    private void registrarUser(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()){
                    Toast.makeText(signup2.this, "Ocurrio un ErrorZZZ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(signup2.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signup2.this, login2.class));
                    finish();
                    
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signup2.this, ""+error, Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", et_name.getText().toString().trim());
                parametros.put("email", et_email.getText().toString().trim());
                parametros.put("contacto", et_phone.getText().toString().trim());
                parametros.put("pwd", et_password.getText().toString().trim());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(signup2.this, login2.class));
        finish();
    }
}