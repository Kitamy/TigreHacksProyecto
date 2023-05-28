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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button btn_Register;
    EditText name, email, password;
    FirebaseFirestore nFirestore;
    FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nFirestore = FirebaseFirestore.getInstance();
        nAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.editRName);
        email = findViewById(R.id.editRCorreo);
        password = findViewById(R.id.editRContra);
        btn_Register = findViewById(R.id.btnRegister);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(Register.this, "Ingresar datos", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(nameUser, emailUser, passUser);
                }

            }
        });
    }

    private void registerUser (String nameUser, String emailUser, String passUser) {
        nAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                String id = nAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nameUser);
                map.put("email", emailUser);
                map.put("password", passUser);

                nFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(Register.this, Login.class));
                        Toast.makeText(Register.this, "Usuario registrado", Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(Register.this, "Error al guardar", Toast.LENGTH_SHORT);

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}