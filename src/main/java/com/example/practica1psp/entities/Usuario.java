package com.example.practica1psp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {
    private String username;
    private String encryptedPass;
    private Rol rol;
}
