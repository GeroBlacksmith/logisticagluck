package com.example.gero.gluck_logistica.domain.model;

/**
 * Created by Osimielci on 18/5/2017.
 */

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String password;
    private String userId;

    private Usuario(Builder builder) {
        id = builder.id;
        nombre = builder.nombre;
        email = builder.email;
        password = builder.password;
        userId = builder.userId;
    }

    public String getNombre() {
        return nombre;
    }



    public String getEmail() {
        return email;
    }



    public String getPassword() {
        return password;
    }



    public String getUserId() {
        return userId;
    }

    public static class Builder{
        private String id;
        private String nombre;
        private String email;
        private String password;
        private String userId;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public Builder email(String email){
            this.email=email;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder userId(String userId){
            this.userId=userId;
            return this;
        }

        public Usuario build(){
            return new Usuario(this);
        }

    }
}
