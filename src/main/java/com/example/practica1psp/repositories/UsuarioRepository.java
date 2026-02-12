package com.example.practica1psp.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.practica1psp.entities.Rol;
import com.example.practica1psp.entities.Usuario;
import com.example.practica1psp.security.PasswordEncryptor;

@Repository
public class UsuarioRepository {
    public List<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("aitor",
        PasswordEncryptor.encrypt("1234"), Rol.ADMIN));
        usuarios.add(new Usuario("alicia",
        PasswordEncryptor.encrypt("1111"), Rol.USER));
        return usuarios;
    }
}