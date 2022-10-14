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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup2 extends AppCompatActivity {

    TextView login;
    EditText et_name, et_email, et_phone, et_password;
    Button btn_Signup;

    FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        //Boton Registrarse
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
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

    private void createUser(){
        String name,email,phone,password;
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        phone = et_phone.getText().toString();
        password = et_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            et_name.setError("El Email no puede estar vacio");
            et_name.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            et_name.setError("La Contraseña no puede estar vacia");
            et_name.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(signup2.this, "Usuario Registrado correctamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup2.this, login2.class));
                        finish();
                    }else {
                        Toast.makeText(signup2.this, "Error de Registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(signup2.this, login2.class));
        finish();
    }
}