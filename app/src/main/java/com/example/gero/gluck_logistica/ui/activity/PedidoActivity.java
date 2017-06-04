package com.example.gero.gluck_logistica.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gero.gluck_logistica.PedidoAdapter;
import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PedidoActivity extends AppCompatActivity {
    ListView lvPedidos;
    DatabaseReference mTablaPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(PedidoActivity.this);
        setContentView(R.layout.activity_pedido);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());
        lvPedidos=(ListView)findViewById(R.id.lv_pedidos);

        PedidoAdapter adapter = new PedidoAdapter(this);
        lvPedidos.setAdapter(adapter);

        mTablaPedido = FirebaseDatabase.getInstance().getReference("Pedido");

        mTablaPedido.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(!dataSnapshot.getKey().equals("dummy")) {


                }else{
                    Toast.makeText(PedidoActivity.this, "I'm dummy", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
