package com.example.gero.gluck_logistica.domain.model;

/**
 * Created by Gero on 25/01/2017.
 */
public class Producto {
    private String id;
    private String marca;
    private String variedad;
    private int capacidad;

    private Producto (Builder builder){
        id = builder.id;
        marca=builder.marca;
        variedad=builder.variedad;
        capacidad=builder.capacidad;
    }

    public String getId(){
        return id;
    }

    public String getMarca(){
        return marca;
    }

    public String getVariedad(){
        return variedad;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public static class Builder{

        private String id;
        private String marca;
        private String variedad;
        private int capacidad;

        public Builder id(String id){
            this.id=id;
            return this;
        }
        public Builder marca(String marca){
            this.marca=marca;
            return this;
        }
        public Builder variedad(String variedad){
            this.variedad=variedad;
            return this;
        }
        public Builder capacidad(int capacidad){
            this.capacidad=capacidad;
            return this;
        }

        public Producto build(){return new Producto(this);}
    }

}
