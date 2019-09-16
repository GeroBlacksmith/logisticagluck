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

    public ProductoAdapter(Context context){
        this.mContext=context;

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
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


        return view;
    }
}
