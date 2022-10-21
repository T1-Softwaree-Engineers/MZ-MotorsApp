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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
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
                registrarUser("https://ochoarealestateservices.com/mzmotors_api/db/register.php");
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

                if(response.equals("empty_name"))
                {
                    Toast.makeText(signup2.this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }
                if(response.equals("empty_password") || response.equals("pass_cannot_be_zero"))
                {
                    Toast.makeText(signup2.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                }
                else if(response.equals("emtpy_contacto"))
                {
                    Toast.makeText(signup2.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }

                else if(response.equals("register_success"))
                {
                    Toast.makeText(signup2.this, "Registro valido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), login2.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signup2.this, error.toString(), Toast.LENGTH_LONG).show();
                String text = error.toString();
                Log.e("error",text);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", et_name.getText().toString());
                parametros.put("email", et_email.getText().toString());
                parametros.put("contacto", et_phone.getText().toString());
                parametros.put("password", et_password.getText().toString());
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