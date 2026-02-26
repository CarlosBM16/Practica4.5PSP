package com.example.practica1psp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{
    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    // Archivo: com.example.practica1psp.security.WebSecurityConfig.java
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, Constans.LOGIN_URL).permitAll()
                // El ADMIN puede borrar
                .requestMatchers(HttpMethod.DELETE, "/contactos/**").hasAuthority("ROLE_ADMIN")
                // ADMIN y USER pueden crear/modificar (POST, PUT)
                .requestMatchers(HttpMethod.POST, "/contactos/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .requestMatchers(HttpMethod.PUT, "/contactos/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                // Cualquier rol (incluido GUEST) puede hacer GET
                .requestMatchers(HttpMethod.GET, "/contactos/**").authenticated()
                .anyRequest().authenticated())
            .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    }
