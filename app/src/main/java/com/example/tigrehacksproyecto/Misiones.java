package com.example.tigrehacksproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Misiones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misiones);
    }

    public void Regreso (View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}