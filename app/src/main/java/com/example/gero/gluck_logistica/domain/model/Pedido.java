package com.example.gero.gluck_logistica.domain.model;

/**
 * Created by Gero on 25/01/2017.
 */

public class Pedido {
    private String id;
    private int cantidad;
    private String nombreProducto;//TODO ver si vale la pena tener clase Producto
    private Pedido(Builder builder){
        id=builder.id;
        cantidad=builder.cantidad;
        nombreProducto=builder.nombreProducto;

    }
    public static class Builder{
        private String id;
        private int cantidad;
        private String nombreProducto;

        public Builder id(String id){
            this.id=id;
            return this;
        }
        public Builder cantidad(int cantidad){
            this.cantidad=cantidad;
            return this;
        }
        public Builder nombreProducto(String nombreProducto){
            this.nombreProducto=nombreProducto;
            return this;
        }
        public Pedido build(){
            return new Pedido(this);
        }
    }

}
