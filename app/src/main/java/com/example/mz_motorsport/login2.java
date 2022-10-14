package com.example.mz_motorsport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login2 extends AppCompatActivity {

    TextView signup;
    TextInputEditText et_username,et_password;
    Button btn_login;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (TextView) findViewById(R.id.login4);
        et_username = (TextInputEditText) findViewById(R.id.et_usarname);
        et_password = (TextInputEditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();

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
                loginUser();
            }
        });


    }

    private void loginUser(){
        String email, password;
        email = et_username.getText().toString();
        password = et_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            et_username.setError("Ingrese un Email");
            et_username.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            et_password.setError("Ingrese su contraseña");
            et_password.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(login2.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login2.this, navigation.class));
                        finish();
                    }else {
                        Toast.makeText(login2.this, "Error de Inicio de Sesion: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}