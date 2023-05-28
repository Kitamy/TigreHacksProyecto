package com.example.tigrehacksproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProxTienda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prox_tienda);
    }

    public void botonVerRecompensas(View view){
        Intent verreco = new Intent(ProxTienda.this, Recompensas.class);
        startActivity(verreco);
    }
}