package com.example.tigrehacksproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    Button btnLogin;
    EditText correo;
    EditText contraseña;
    FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.editName);
        contraseña = findViewById(R.id.editMuni);
        btnLogin =  findViewById(R.id.btnlog);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = correo.getText().toString().trim();
                String passUser = contraseña.getText().toString().trim();

                if(emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(Login.this, "Ingresar datos", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(emailUser, passUser);
                }
            }

        });

    }

    private void loginUser(String emailUser, String passUser){
        nAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Login.this, "Error al Logearse", Toast.LENGTH_SHORT).show();

            }
        });
    }
}