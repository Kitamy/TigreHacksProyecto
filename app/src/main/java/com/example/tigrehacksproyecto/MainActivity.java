package com.example.tigrehacksproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.net.Uri;
import com.bumptech.glide.Glide;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    ImageButton siguiente;
    ImageView imagepet;
    float xDown = 0, yDown = 0;



    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imagepet = findViewById(R.id.imageVP);
        String url="https://media.tenor.com/FfHjNfODLkgAAAAi/buizel-pokemon.gif";
        Uri urlparse = Uri.parse(url);
        Glide.with(getApplicationContext()).load(urlparse).into(imagepet);

        imagepet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY;
                        movedX = event.getX();
                        movedY = event.getY();

                        float distanceX = movedX - xDown;
                        float distanceY = movedY - yDown;

                        imagepet.setX(imagepet.getX() + distanceX);
                        imagepet.setY(imagepet.getY() + distanceY);
                        break;
                }

                return true;
            }
        });

    }

    public void BotonMision(View view){
        Intent botonmision = new Intent(MainActivity.this, Misiones.class);
        startActivity(botonmision);
    }

    public void BotonRecompensa(View view){
        Intent botonrecompensa = new Intent(MainActivity.this, Recompensas.class);
        startActivity(botonrecompensa);
    }

    public void BotonRanking(View view) {
        Intent botonranking = new Intent(MainActivity.this, Ranking.class);
        startActivity(botonranking);
    }

    public void BotonContaminacion(View view) {
        Intent botoncontaminacion = new Intent(MainActivity.this, Contaminacion.class);
        startActivity(botoncontaminacion);
    }

    public void BotonTienda(View view) {
        Intent botontienda = new Intent(MainActivity.this, ProxTienda.class);
        startActivity(botontienda);
    }

}