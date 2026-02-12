package com.example.practica1psp.entities;

public enum Rol {
    ADMIN("ADMIN"),
    USER("USER");

    private final String rol;

    Rol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
