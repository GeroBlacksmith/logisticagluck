package com.example.gero.gluck_logistica.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;

public class StockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(StockActivity.this);
        setContentView(R.layout.item_producto);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());
    }
}
