package com.example.gero.gluck_logistica.domain.model;

/**
 * Created by Gero on 21/01/2017.
 */

public class Cliente {
    public int _id;
    public String nombre;
    public String direccion;// Calle-numero ó manzana/hta-lote/casa ó Ruta nro-kilometro
    public String telefono;// TODO crear regex para varias ciudades +549 299 5 123456
    public String mail;

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }

    public String firebaseKey;
    private static final String TABLE_CLIENTES = "cliente";

    public Cliente(){

    }

    public Cliente(int id, String nombre, String direccion, String telefono, String mail){
        this._id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
    }
    public Cliente( String nombre, String direccion, String telefono, String mail){

        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
    }

    public int getID() {
        return _id;
    }

    public void setID(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreTabla(){ return this.TABLE_CLIENTES; }
}
