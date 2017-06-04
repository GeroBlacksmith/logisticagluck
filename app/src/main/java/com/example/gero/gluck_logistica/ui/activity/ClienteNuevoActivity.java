package com.example.gero.gluck_logistica.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.example.gero.gluck_logistica.domain.model.Cliente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ClienteNuevoActivity extends AppCompatActivity implements TextWatcher {

    private Button ingresarCliente;

    private EditText etNombre;
    private EditText etDireccion;
    private EditText etTelefono;
    private EditText etMail;

    private boolean habilitado = false;

    private View.OnClickListener accionIngresarCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent agregarClienteIntent = new Intent(ClienteNuevoActivity.this, ClientesActivity.class);
            //DBHandler dbHandler = new DBHandler(ClienteNuevoActivity.this);
            Cliente cliente = new Cliente();

            cliente.setNombre(etNombre.getText().toString());
            cliente.setDireccion(etDireccion.getText().toString());
            cliente.setTelefono(etTelefono.getText().toString());
            cliente.setMail(etMail.getText().toString());
            //dbHandler.addCliente(cliente);

            HashMap<String, String> nuevoCliente = new HashMap<String, String>();
            nuevoCliente.put("nombre", cliente.getNombre());
            nuevoCliente.put("telefono", cliente.getTelefono());
            nuevoCliente.put("mail", cliente.getMail());
            nuevoCliente.put("direccion", cliente.getDireccion());
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Cliente");
            mDatabase.push().setValue(nuevoCliente);
            startActivity(agregarClienteIntent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Opcion.Grafica.pantallaCompleta(ClienteNuevoActivity.this);
        setContentView(R.layout.activity_cliente_nuevo);
        Opcion.Grafica.ocultarActionBar(getSupportActionBar());
        ingresarCliente = (Button) findViewById(R.id.btn_agregar_cliente);
        //TODO validar campos con TextWatcher e informar de errores con TextView.setError
        //ej etNombre.setError("Mal ingreso de campo");
        etNombre = (EditText) findViewById(R.id.et_agregar_cliente_nombre);
        etNombre.addTextChangedListener(this);

        etDireccion = (EditText) findViewById(R.id.et_agregar_cliente_direccion);
        etDireccion.addTextChangedListener(this);

        etTelefono = (EditText) findViewById(R.id.et_agregar_cliente_telefono);
        etTelefono.addTextChangedListener(this);

        etMail = (EditText) findViewById(R.id.et_agregar_cliente_mail);
        etMail.addTextChangedListener(this);
        //<if habilitado>

        //<define el evento click del boton>
        ingresarCliente.setOnClickListener(accionIngresarCliente);
        //</define el evento click del boton>

        //</if habilitado>

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        Log.d("DEMON btc:","ok");

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("DEMON otc:","ok");

    }

    @Override
    public void afterTextChanged(Editable s) {
        //si habilitado el botton es enable y si no es disable.
        Log.d("DEMON atc:","ok");
        Button btnAgregarCliente = (Button) findViewById(R.id.btn_agregar_cliente);
        String errorVacio = "No puede estar vacio";
        boolean flagNombre = etNombre.getText().toString().trim() == "";
        boolean flagDireccion = etDireccion.getText().toString().trim() == "";
        boolean flagTelefono = etTelefono.getText().toString().trim() == "";
        boolean flagMail = etMail.getText().toString().trim() == "";
        Log.d("DEMON nom otc:",etNombre.getText().toString().trim());
        if(etNombre.getText().toString().trim().isEmpty()||etNombre.getText().toString().trim().isEmpty()||etMail.getText().toString().trim().isEmpty()||etTelefono.getText().toString().trim().isEmpty()
                ) {
            if (etNombre.getText().toString().trim().isEmpty()) {
                etNombre.setError(errorVacio);

                Log.d("DEMON if nom:", "ok");
            }
            if (etDireccion.getText().toString().trim().isEmpty()) {
                etDireccion.setError(errorVacio);

                Log.d("DEMON if dir:", "ok");
            }
            if (etMail.getText().toString().trim().isEmpty()) {
                etMail.setError(errorVacio);

                Log.d("DEMON if mail:", "ok");
            }
            if (etTelefono.getText().toString().trim().isEmpty()) {
                etTelefono.setError(errorVacio);

                Log.d("DEMON if tel:", "ok");
            }
            btnAgregarCliente.setEnabled(false);
        }else{
            btnAgregarCliente.setEnabled(true);
        }


    }
}
