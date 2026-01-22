package com.example.practica1psp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practica1psp.entities.Contacto;
import com.example.practica1psp.services.ContactoService;

@RestController
@RequestMapping("/contactos")
public class ContactoController {
    private final ContactoService contactoService;

    @Autowired
    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping
    public List<Contacto> obtenerTodos() {
        return contactoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Contacto obtenerPorId(@PathVariable Long id) {
        return contactoService.obtenerPorId(id);
    }

    @PostMapping
    public Contacto guardar(@RequestBody Contacto contacto) {
        return contactoService.guardar(contacto);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        contactoService.eliminar(id);
    }

    @PutMapping("/{id}")
    public void modificar(@PathVariable Long id, @RequestBody Contacto contacto) {
        contactoService.modificar(id, contacto);
    }
}
