package com.example.mz_motorsport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login2 extends AppCompatActivity {

    TextView signup;
    TextInputEditText et_username, et_password;
    Button btn_login;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (TextView) findViewById(R.id.login4);
        et_username = (TextInputEditText) findViewById(R.id.et_usarname);
        et_password = (TextInputEditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btnlogin);


        //Ir al SignUp
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login2.this, signup2.class));
                finish();
            }
        });

        //Boton para Iniciar sesion
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = et_username.getText().toString();
                password = et_password.getText().toString();
                if (email.isEmpty()){
                    Toast.makeText(login2.this, "Ingrese su correo", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(login2.this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser("http://192.168.50.166/APP/validation.php");
                }

            }
        });


    }

    private void loginUser(String URL)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){
                    guardarSession(email, password);
                    startActivity(new Intent(login2.this, navigation.class));
                    finish();
                }else{
                    Toast.makeText(login2.this, "Usuario o Contraseña incorrectas", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login2.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("email", email);
                parametros.put("password", password);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        /*-----------------------------------------------------------------------------------------------------------

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String test = response.toString();
                Log.e("Erro: ", test);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login2.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(jsonObjectRequest);*/
    }

    private void guardarSession(String uEmail, String uPassword) {
        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=datosU.edit();
        editor.putString("email",uEmail);
        editor.putString("password",uPassword);
        editor.putBoolean("session",true);
        editor.commit();
    }
}