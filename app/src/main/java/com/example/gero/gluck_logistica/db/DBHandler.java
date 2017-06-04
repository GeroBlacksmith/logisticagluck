package com.example.gero.gluck_logistica.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gero.gluck_logistica.domain.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gero on 28/01/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "gluckDB";

    private static final String TABLE_CLIENTES = new Cliente().getNombreTabla();

    private static final String KEY_ID="_id";
    private static final String KEY_NOMBRE="nombre";
    private static final String KEY_DIRECCION="direccion";
    private static final String KEY_TELEFONO="telefono";
    private static final String KEY_MAIL="mail";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREA_TABLA_CLIENTE="CREATE TABLE "+TABLE_CLIENTES
                +"( "+
                KEY_ID + " INTEGER PRIMARY KEY, "+
                KEY_NOMBRE + " TEXT, "+
                KEY_DIRECCION+ " TEXT, "+
                KEY_TELEFONO+ " TEXT, "+
                KEY_MAIL+ " TEXT)";

        db.execSQL(CREA_TABLA_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLIENTES);
        onCreate(db);
    }

    public void addCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOMBRE, cliente.getNombre());
        values.put(KEY_DIRECCION, cliente.getDireccion());
        values.put(KEY_TELEFONO, cliente.getTelefono());
        values.put(KEY_MAIL, cliente.getMail());
        db.insert(TABLE_CLIENTES, null, values);
        db.close();
    }

    public Cliente getCliente(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE_CLIENTES, new String[]{
                    KEY_ID, KEY_NOMBRE, KEY_DIRECCION, KEY_TELEFONO, KEY_MAIL
            }, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            Cliente cliente = new Cliente(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));
            return cliente;
        }catch(SQLiteException se){
            Log.e("sqliteEx: ", se.getMessage());
        }


        return null;
    }

    public List<Cliente> getAllClientes(){
        List<Cliente> listaDeClientes = new ArrayList<Cliente>();
        String selectQuery = "SELECT  * FROM "+TABLE_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor=db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Cliente cliente = new Cliente();
                cliente.setID(Integer.parseInt(cursor.getString(0)));
                cliente.setNombre(cursor.getString(1));
                cliente.setDireccion(cursor.getString(2));
                cliente.setTelefono(cursor.getString(3));
                cliente.setMail(cursor.getString(4));
                listaDeClientes.add(cliente);
            }while(cursor.moveToNext());

        }
        return listaDeClientes;

    }

    public int getClientesCount(){
        String countQuery =  "SELECT  * FROM "+TABLE_CLIENTES;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int updateCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, cliente.getID());
        values.put(KEY_NOMBRE, cliente.getNombre());
        values.put(KEY_DIRECCION, cliente.getDireccion());
        values.put(KEY_TELEFONO, cliente.getTelefono());
        values.put(KEY_MAIL, cliente.getMail());
        return db.update(TABLE_CLIENTES, values, KEY_ID+" = ?",
                new String[]{String.valueOf(cliente.getID())});

    }

    public void deleteCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("id", String.valueOf(cliente.getID()));
        db.delete(TABLE_CLIENTES, KEY_ID + " = ?",
                new String[] { String.valueOf(cliente.getID()) });
        /*db.delete(TABLE_CLIENTES, KEY_NOMBRE + " = ?, "+KEY_DIRECCION + " = ?, "+KEY_TELEFONO + " = ?, "+KEY_MAIL + " = ?",
                new String[] { cliente.getNombre(),cliente.getDireccion(),cliente.getTelefono(),cliente.getMail() });*/
        /*db.delete(TABLE_CLIENTES,
                KEY_NOMBRE + " = "+cliente.getNombre()+ " , " +
                        KEY_DIRECCION + " = "+cliente.getDireccion()+" , "+
                        KEY_TELEFONO + " = "+cliente.getTelefono()+", "+
                        KEY_MAIL + " = " +cliente.getMail(),
                new String[]{});*/
        db.close();
    }
    public boolean estaVacia(String tabla){

        if (tabla==TABLE_CLIENTES){
                SQLiteDatabase db = this.getWritableDatabase();
                String count = "SELECT count(*) FROM "+TABLE_CLIENTES;
                Cursor mcursor = db.rawQuery(count, null);
                mcursor.moveToFirst();
                int icount = mcursor.getInt(0);
                if(icount==0){
                    return true;
                }
        }
        return false;
    }
    public int[] getAllIds(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+KEY_ID+" FROM "+TABLE_CLIENTES;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int size=cursor.getCount();
        if(size!=0) {
            int[] ids = new int[size];
            for(int i=0; i<size;i++){
                ids[i]=cursor.getInt(0);
            }
            return ids;

        }else{
            return new int[]{0};
        }

    }

    public Cursor getQueryResult(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT nombre FROM "+TABLE_CLIENTES;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
