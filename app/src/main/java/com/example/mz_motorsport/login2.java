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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
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

        signup = findViewById(R.id.login4);
        et_username = findViewById(R.id.et_usarname);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btnlogin);


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
                }else if(password.isEmpty()){
                    Toast.makeText(login2.this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser("https://ochoarealestateservices.com/mzmotors/users.php?email="+email+"&pwd="+password);
                }

            }
        });


    }

    private void loginUser(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = response.getJSONObject(0);
                    String uName = jsonObject.getString("nombre");
                    String uEmail = jsonObject.getString("email");
                    String uPhone = jsonObject.getString("contacto");
                    String uFoto = jsonObject.getString("fperfil");
                    Toast.makeText(login2.this, "Inicio Exitoso", Toast.LENGTH_SHORT).show();
                    guardarSession(uName,uEmail,uPhone, uFoto);
                    startActivity(new Intent(login2.this, navigation.class));
                    finish();
                }catch (JSONException e){
                    Toast.makeText(login2.this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login2.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(jsonArrayRequest);

    }

    private void guardarSession(String uName, String uEmail, String uPhone, String uFoto) {
        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=datosU.edit();
        editor.putString("nombre",uName);
        editor.putString("email",uEmail);
        editor.putString("phone",uPhone);
        editor.putString("FotoP",uFoto);
        editor.putBoolean("session",true);
        editor.commit();
    }
}