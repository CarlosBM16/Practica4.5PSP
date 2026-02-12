package com.example.practica1psp.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.practica1psp.entities.Usuario;
import com.example.practica1psp.repositories.UsuarioRepository;
import com.example.practica1psp.security.Constans;
import com.example.practica1psp.security.JWTAuthenticationConfig;
import com.example.practica1psp.security.PasswordEncryptor;

@RestController
public class LoginController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;

    @PostMapping("login")
    public String login(@RequestParam("user") String username, @RequestParam("encryptedPass") String encryptedPass) throws BadRequestException {
        List<Usuario> usuarios = usuarioRepository.getUsuarios();
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && PasswordEncryptor.decrypt(usuario.getEncryptedPass()).equals(encryptedPass)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            throw new BadRequestException();
        }
        
        String token = jwtAuthtenticationConfig.getJWTToken(usuarioEncontrado.getUsername(), usuarioEncontrado.getRol());

        return token;
    }
}
