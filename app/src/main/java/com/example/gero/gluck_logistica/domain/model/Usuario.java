package com.example.gero.gluck_logistica.domain.model;

/**
 * Created by Osimielci on 18/5/2017.
 */

public class Usuario {
    String nombre;
    String email;
    String password;
    String userId;

    public Usuario(String nombre, String email, String password, String userId) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
