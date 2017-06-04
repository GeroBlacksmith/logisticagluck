package com.example.gero.gluck_logistica.ui.activity;

/****
 * ClienteDetalleActivity.java
 * * Actividad comenzada desde ClienteActivity.java
 * * Extras del intent:
 * * * String nombre
 * * * String telefono
 * * * String direccion
 * * * String mail
 * * * String firebaseKey
 *
 * layout principal: item_cliente.xml
 * * Elementos del layout:
 * * * TextView id:tv_item_cliente_nombre
 * * * TextView id:tv_item_cliente_telefono
 * * * TextView id:tv_item_cliente_direccion
 * * * TextView id:tv_item_cliente_mail
 * * * TextView id:tv_hidden_firebase_key
 * * * Button id:btn_item_borrar_cliente
 * */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.example.gero.gluck_logistica.domain.model.Cliente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClienteDetalleActivity extends AppCompatActivity {
    int pos=0;
    Cliente oCliente;
    Button botonBorrar, botonAgregarPedido;
    TextView tvNombre ,
    tvDireccion ,
    tvTelefono ,
    tvMail ,
     tvFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(ClienteDetalleActivity.this);
        setContentView(R.layout.item_cliente);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());

        Intent intent = getIntent();
        String nombre=intent.getStringExtra("nombre");
        String direccion=intent.getStringExtra("direccion");
        String telefono=intent.getStringExtra("telefono");
        String mail=intent.getStringExtra("mail");
        final String firebaseKey = intent.getStringExtra("key");




        tvNombre = (TextView)findViewById(R.id.tv_item_cliente_nombre);
        tvDireccion = (TextView)findViewById(R.id.tv_item_cliente_direccion);
        tvTelefono = (TextView)findViewById(R.id.tv_item_cliente_telefono);
        tvMail = (TextView)findViewById(R.id.tv_item_cliente_mail);
        tvFirebase = (TextView)findViewById(R.id.tv_hidden_firebase_key);
        //Log.d("Demon hidden", firebaseKey);

        botonBorrar =(Button)findViewById(R.id.btn_item_borrar_cliente);

        tvNombre.setText(nombre);
        tvDireccion.setText(direccion);
        tvTelefono.setText(telefono);
        tvMail.setText(mail);
        //tvFirebase.setText(firebaseKey);

        //Log.d("ID", String.valueOf(cliente.getItem(pos).getID()));
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Cliente").child(firebaseKey).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().setValue(null);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Gluck-Logistica", "borrarCliente:onCancelled", databaseError.toException());
                    }
                });
                //necesito id del registro para borrarlo.



                //Toast.makeText(ClienteDetalleActivity.this, key, Toast.LENGTH_LONG).show();
                //Log.d("key; ", key);

                Toast.makeText(ClienteDetalleActivity.this, "Removed", Toast.LENGTH_LONG).show();
                Intent backToMainIntent = new Intent(ClienteDetalleActivity.this, MainActivity.class);
                startActivity(backToMainIntent);

/*
*                 DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                builder.setMessage("¿Realmente desea borrar este cliente?")
                        .setPositiveButton("Si", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
*/
                //DBHandler dbh = new DBHandler(getApplicationContext());
                //Log.d("Nombre:",oCliente.getNombre());
               // dbh.deleteCliente(oCliente);

            }
        });

        botonAgregarPedido = (Button)findViewById(R.id.btn_item_agregar_pedido_cliente);
        botonAgregarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent haciaPedido = new Intent(ClienteDetalleActivity.this, PedidoActivity.class);
                String nombreDelCliente = tvNombre.getText().toString();
                //TODO Crear nueva avtivity PedidoNuevo
                //TODO implementar Pedido en la base de datos

            }
        });
    }
}
