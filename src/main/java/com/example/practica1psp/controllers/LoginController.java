package com.example.practica1psp.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.practica1psp.security.Constans;
import com.example.practica1psp.security.JWTAuthenticationConfig;

@RestController
public class LoginController {
    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;
    @PostMapping("login")
    public String login(@RequestParam("user") String username, @RequestParam("encryptedPass") String encryptedPass) throws BadRequestException {
        if(! (username.equals(Constans.USER) && encryptedPass.equals(Constans.PASS))){
            throw new BadRequestException();
        }

        String token = jwtAuthtenticationConfig.getJWTToken(username);
        
        return token;
    }
}
