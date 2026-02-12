package com.example.practica1psp.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import static com.example.practica1psp.security.Constans.SUPER_SECRET_KEY;
import static com.example.practica1psp.security.Constans.TOKEN_EXPIRATION_TIME;
import static com.example.practica1psp.security.Constans.getSigningKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JWTAuthenticationConfig {
    public String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");
        
            String token;
            
        token = Jwts
                .builder()
                .setId("espinozajgeJWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(SUPER_SECRET_KEY),
                        SignatureAlgorithm.HS512).compact();
        
                        return "Bearer " + token;
    }
}