package com.example.gero.gluck_logistica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gero.gluck_logistica.domain.model.Pedido;

/**
 * Created by Gero on 25/01/2017.
 */

public class PedidoAdapter extends BaseAdapter {
    private Context mContext;
    private Pedido[] listaPedidos={
            new Pedido(),new Pedido(),new Pedido()
    };

    public PedidoAdapter(Context context){
        this.mContext=context;

        //cargar el array
        for(int i=0;i<listaPedidos.length;i++){
            ((Pedido)(listaPedidos[i])).cantidad= (int) (Math.random()*40);
            ((Pedido)(listaPedidos[i])).nombreProducto= "Producto " + String.valueOf(i);
        }
    }

    @Override
    public int getCount() {
        return listaPedidos.length;
    }

    @Override
    public Object getItem(int position) {
        return listaPedidos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null) {

            LayoutInflater inflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_pedidos, null);


        }

        TextView tvNombreProducto = (TextView)view.findViewById(R.id.tv_pedido_producto);
        TextView tvCantidad = (TextView)view.findViewById(R.id.tv_pedido_producto_cantidad);


        tvNombreProducto.setText(listaPedidos[position].nombreProducto);
        tvCantidad.setText(String.valueOf(listaPedidos[position].cantidad));

        return view;
    }
}
