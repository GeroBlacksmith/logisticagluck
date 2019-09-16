package com.example.gero.gluck_logistica.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gero.gluck_logistica.ClienteAdapter;
import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.example.gero.gluck_logistica.domain.model.Cliente;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

//<class ClientesActivity>
public class ClientesActivity extends AppCompatActivity {
    //<variables de la clase>
    private ListView lvCliente;

    private DatabaseReference mDatabase;

    private ArrayList<String> mUsername = new ArrayList<>();
    private ArrayList<String> mKey = new ArrayList<>();

    private View.OnClickListener clickAgregarCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ClientesActivity.this, ClienteNuevoActivity.class);
            startActivity(i);
        }
    };

    private AdapterView.OnItemClickListener clickItemDeListaCliente = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            TextView nombre = (TextView) view.findViewById(R.id.tv_cliente_nombre);
            TextView direccion = (TextView) view.findViewById(R.id.tv_cliente_direccion);
            TextView telefono = (TextView) view.findViewById(R.id.tv_cliente_telefono);
            TextView mail = (TextView) view.findViewById(R.id.tv_cliente_mail);
            TextView firebaseKy = (TextView) view.findViewById(R.id.tv_hidden_firebase_key);

            Intent verDetalleCliente = new Intent(ClientesActivity.this, ClienteDetalleActivity.class);
            verDetalleCliente.putExtra("nombre", nombre.getText().toString());
            verDetalleCliente.putExtra("direccion", direccion.getText().toString());
            verDetalleCliente.putExtra("telefono", telefono.getText().toString());
            verDetalleCliente.putExtra("mail", mail.getText().toString());
            verDetalleCliente.putExtra("key", firebaseKy.getText().toString());
            startActivity(verDetalleCliente);

        }
    };
    //</variables de la clase>

    //<onCreate>
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(ClientesActivity.this);
        setContentView(R.layout.activity_clientes);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());
        Button agregarCliente = (Button) findViewById(R.id.boton_clientes_agregar);

        agregarCliente.setOnClickListener(clickAgregarCliente);

        lvCliente = (ListView) findViewById(R.id.lv_clientes_agregados);

        lvCliente.setOnItemClickListener(clickItemDeListaCliente);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Cliente");

        final ArrayList<Cliente> mClientes = new ArrayList<>();
        final ClienteAdapter adapter = new ClienteAdapter(this, mClientes);
        //mDatabase.child("Cliente").child("01").setValue("Gerardo");

        ///final ArrayAdapter<String> arrayADapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsername);
        lvCliente.setAdapter(adapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                //if(!snapshot.getKey().equals("schema")){

                adapter.notifyDataSetChanged();
                //}

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = mKey.indexOf(key);
                mUsername.set(index, value);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(ClientesActivity.this, "onChildRemoved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //<onCreate>


}
//</class ClientesActivity>