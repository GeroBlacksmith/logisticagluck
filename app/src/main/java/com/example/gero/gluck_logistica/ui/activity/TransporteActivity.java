package com.example.gero.gluck_logistica.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;

public class TransporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(TransporteActivity.this);
        setContentView(R.layout.activity_transporte);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());
    }
}
