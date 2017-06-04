package com.example.gero.gluck_logistica.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gero.gluck_logistica.ProductoAdapter;
import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;

public class ProductoActivity extends AppCompatActivity {
    ListView lvProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(ProductoActivity.this);
        setContentView(R.layout.activity_producto);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());
        lvProductos=(ListView)findViewById(R.id.lv_producto);
        ProductoAdapter adapter = new ProductoAdapter(this);
        lvProductos.setAdapter(adapter);

    }
}
