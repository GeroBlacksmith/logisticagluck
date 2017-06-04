package com.example.gero.gluck_logistica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gero.gluck_logistica.domain.model.Producto;

/**
 * Created by Gero on 27/01/2017.
 */

public class ProductoAdapter extends BaseAdapter{
    private Context mContext;
    private Producto[] productos={
            new Producto(),
            new Producto(),
            new Producto(),

    };
    public ProductoAdapter(Context context){
        this.mContext=context;
        productos[0].marca="Puel";
        productos[1].marca="Bandaccari";
        productos[2].marca="Bandaccari";
        productos[0].variedad="trigo";
        productos[1].variedad="IPA";
        productos[2].variedad="Golden";
        productos[2].capacidad=1000;
        productos[1].capacidad=1000;
        productos[0].capacidad=1000;
    }
    @Override
    public int getCount() {
        return this.productos.length;
    }

    @Override
    public Object getItem(int position) {
        return this.productos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view==null){

            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_producto, null);
        }
        TextView tvMarca = (TextView)view.findViewById(R.id.tv_producto_marca);
        TextView tvVariedad = (TextView)view.findViewById(R.id.tv_producto_variedad);
        TextView tvCapacidad = (TextView)view.findViewById(R.id.tv_producto_capacidad);

        tvCapacidad.setText(String.valueOf(productos[position].capacidad));
        tvMarca.setText(productos[position].marca);
        tvVariedad.setText(productos[position].variedad);

        return view;
    }
}
