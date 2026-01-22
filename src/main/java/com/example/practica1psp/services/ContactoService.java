package com.example.practica1psp.services;

import java.util.List;

import com.example.practica1psp.entities.Contacto;

public interface ContactoService {
    List<Contacto> obtenerTodos();
    Contacto obtenerPorId(Long id);
    Contacto guardar(Contacto contacto);
    Contacto modificar(Long id, Contacto contacto);
    void eliminar(Long id);
}
