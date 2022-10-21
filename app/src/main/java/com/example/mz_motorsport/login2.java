package com.example.mz_motorsport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class login2 extends AppCompatActivity {

    TextView signup;
    TextInputEditText et_username, et_password;
    Button btn_login;


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
                loginUser("https://ochoarealestateservices.com/mzmotors_api/db/validation.php");
            }
        });


    }

    private void loginUser(String URL)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("no_email"))
                {
                    Toast.makeText(login2.this, "Ingrese un email", Toast.LENGTH_SHORT).show();
                }
                if(response.equals("empty_password"))
                {
                    Toast.makeText(login2.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                }
                else if(response.equals("empty_data"))
                {
                    Toast.makeText(login2.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }

                else if(response.equals("1"))
                {
                    Intent intent = new Intent(getApplicationContext(), navigation.class);
                    startActivity(intent);
                    finish();
                }
                else if(response.equals("no_verif"))
                {
                    Toast.makeText(login2.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login2.this, error.toString(), Toast.LENGTH_LONG).show();
                String text = error.toString();
                Log.e("error",text);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("email", et_username.getText().toString());
                parametros.put("password", et_password.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}