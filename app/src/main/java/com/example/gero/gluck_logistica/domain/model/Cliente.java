package com.example.gero.gluck_logistica.domain.model;

/**
 * Created by Gero on 21/01/2017.
 */

public class Cliente {
    private String id;
    private String nombre;
    private String direccion;// Calle-numero ó manzana/hta-lote/casa ó Ruta nro-kilometro
    private String telefono;// TODO crear regex para varias ciudades +549 299 5 123456
    private String email;

    private Cliente(Builder builder){
        id = builder.id;
        nombre = builder.nombre;
        direccion = builder.direccion;
        telefono = builder.telefono;
        email = builder.email;
    }

    public String getId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getMail(){
        return email;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String id;
        private String nombre;
        private String direccion;
        private String telefono;
        private String email;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public Builder direccion(String direccion){
            this.direccion=direccion;
            return this;
        }

        public Builder telefono(String telefono){
            this.telefono=telefono;
            return this;
        }

        public Builder mail(String email){
            this.email=email;
            return this;
        }

        public Cliente build(){
            return new Cliente(this);
        }
    }

}
