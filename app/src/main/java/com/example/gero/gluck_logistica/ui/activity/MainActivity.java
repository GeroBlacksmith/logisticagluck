package com.example.gero.gluck_logistica.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//<class MainActivity>
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Firebase Debug";
    //Definicion de las variables para la autenticacion en firebase

    //<firebase auth>
    private FirebaseAuth mAuth;
    //</firebase auth>

    //<firebase auth listener>
    private FirebaseAuth.AuthStateListener mAuthListener;
    //</firebase auth listener>

    //<onCreate method>
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO Autenticacion con inicio de session


        //<constructor heredado>
        super.onCreate(savedInstanceState);
        //</constructor heredado>
        // remove title


        Opcion.Grafica.pantallaCompleta(MainActivity.this);

        //<define el layout para esta activity res/layout/activity_main.xml>
        setContentView(R.layout.activity_main);
        //</define el layout para esta activity>

        Opcion.Grafica.ocultarActionBar(getSupportActionBar());


        //<mAuth>
        mAuth = FirebaseAuth.getInstance();
        //</mAuth>

        //<mAuthListener>
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

            }
        };
        //</mAuthListener>


        //<define todos los elementos "Button" de la activity>
        Button btnClientes = (Button) findViewById(R.id.boton_clientes);//<para ir a ClientesActivity/>
        Button btnPedido = (Button) findViewById(R.id.boton_pedidos);//<para ir a PedidoActivity/>
        Button btnProducto = (Button) findViewById(R.id.boton_productos);//<para ir a ProductoActivity/>
        Button btnRuta = (Button) findViewById(R.id.boton_rutas);//<para ir a RutaActivity/>
        Button btnStock = (Button) findViewById(R.id.boton_stock);//<para ir a StockActivity/>
        Button btnTransporte = (Button) findViewById(R.id.boton_transportes);//<para ir a TransporteActivity />
        //TODO model Empleado
        //</define todos los Button de la activity>

        //<start ClientesActivity>
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCliente = new Intent(MainActivity.this, ClientesActivity.class);
                startActivity(intentCliente);
            }
        });
        //</start ClienteActivity>


        //<start PedidoActivity>
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCliente = new Intent(MainActivity.this, PedidoActivity.class);
                startActivity(intentCliente);
            }
        });
        //</start>


        //<start ProductoActivity>
        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCliente = new Intent(MainActivity.this, ProductoActivity.class);
                startActivity(intentCliente);
            }
        });
        //</start>


        //<start RutaActivity>
        btnRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCliente = new Intent(MainActivity.this, RutaActivity.class);
                startActivity(intentCliente);
            }
        });
        //</start>


        //<start StockActivity>
        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCliente = new Intent(MainActivity.this, StockActivity.class);
                startActivity(intentCliente);
            }
        });
        //</start>


        //<start TransporteActivity>
        btnTransporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCliente = new Intent(MainActivity.this, TransporteActivity.class);
                startActivity(intentCliente);
            }
        });
        //</start>
    }
    //</onCreate method>

    //<onStart method>
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    //</onStart method>

    //<onStop method>
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    //</onStop method>
}
//</class MainActivity>