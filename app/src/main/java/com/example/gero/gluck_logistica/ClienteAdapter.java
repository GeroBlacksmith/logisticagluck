package com.example.gero.gluck_logistica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gero.gluck_logistica.domain.model.Cliente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Gero on 21/01/2017.
 */


public class ClienteAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Cliente> listaClientes=new ArrayList<>();

    public ClienteAdapter(Context context, ArrayList<Cliente> mClientes){
        this.mContext=context;
        this.listaClientes=mClientes;

    }
    @Override
    public int getCount() {
        return listaClientes.size();
    }

    @Override
    public Cliente getItem(int position) {
        return listaClientes.get(position);
    }

    @Override
    public long getItemId(int position){

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_cliente, null);
        }
        TextView tvNombre = (TextView)convertView.findViewById(R.id.tv_cliente_nombre);
        TextView tvDireccion = (TextView)convertView.findViewById(R.id.tv_cliente_direccion);
        TextView tvTelefono = (TextView)convertView.findViewById(R.id.tv_cliente_telefono);
        TextView tvMail = (TextView)convertView.findViewById(R.id.tv_cliente_mail);
        TextView tvFirebaseKey = (TextView)convertView.findViewById(R.id.tv_hidden_firebase_key);

        tvNombre.setText(listaClientes.get(position).getNombre());
        tvDireccion.setText(listaClientes.get(position).getDireccion());
        tvTelefono.setText(listaClientes.get(position).getTelefono());
        tvMail.setText(listaClientes.get(position).getMail());
        tvFirebaseKey.setText(listaClientes.get(position).getFirebaseKey());
        return convertView;
    }

    public View getViewByFirebaseKey(final String firebaseKey, View view, ViewGroup parent){
        //TODO crear clases para modular los llamados a bases de datos.
        if(view==null){
            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.activity_cliente, null);
        }
        DatabaseReference dbFirebase = FirebaseDatabase.getInstance().getReference().child("Cliente");
        final View finalView = view;
        dbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()==firebaseKey){
                    TextView tvNombre = (TextView) finalView.findViewById(R.id.tv_cliente_nombre);
                    TextView tvDireccion = (TextView) finalView.findViewById(R.id.tv_cliente_direccion);
                    TextView tvTelefono = (TextView) finalView.findViewById(R.id.tv_cliente_telefono);
                    TextView tvMail = (TextView) finalView.findViewById(R.id.tv_cliente_mail);
                    TextView tvFirebaseKey = (TextView) finalView.findViewById(R.id.tv_hidden_firebase_key);
                    tvNombre.setText(dataSnapshot.child("nombre").toString());
                    tvDireccion.setText(dataSnapshot.child("direccion").toString());
                    tvTelefono.setText(dataSnapshot.child("telefono").toString());
                    tvMail.setText(dataSnapshot.child("telefono").toString());
                    tvFirebaseKey.setText(firebaseKey);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      return finalView;
    }
}


