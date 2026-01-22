package com.example.practica1psp.client;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.practica1psp.entities.Contacto;

public class TestingClient {
    private static final String BASE_URL =
    "http://localhost:8080/contactos";
    public static void main(String[] args) {
    // Crea un nuevo contacto para agregar
        Contacto nuevoContacto = new Contacto("Juan", "987654321");
        // Ejecuta el cliente de prueba
        TestingClient cliente = new TestingClient();
        cliente.realizarPruebas(nuevoContacto);
    }
    public void realizarPruebas(Contacto nuevoContacto) {
        // Agrega un nuevo contacto
        nuevoContacto = agregarContacto(nuevoContacto);
        System.out.println("Contacto agregado: " + nuevoContacto);
        // Obtiene todos los contactos
        listarContactos();
        // Obtiene un contacto por ID
        obtenerContactoPorId(nuevoContacto.getId());
        // Elimina un contacto por ID
        eliminarContacto(nuevoContacto.getId());
        //Modifica un contacto
        modificarContacto(1L, new Contacto("Carlos", "123456789"));
        // Obtiene todos los contactos después de la eliminación
        listarContactos();
    }

    private Contacto agregarContacto(Contacto contacto) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contacto> response =
        restTemplate.postForEntity(BASE_URL, contacto, Contacto.class);
        return response.getBody();
    }

    private void listarContactos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contacto[]> response =
        restTemplate.getForEntity(BASE_URL, Contacto[].class);
        Contacto[] contactos = response.getBody();
        System.out.println("Lista de contactos:");

        for (Contacto contacto : contactos) {
            System.out.println(contacto);
        }
    }

    private void obtenerContactoPorId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Contacto contacto = restTemplate.getForObject(BASE_URL + "/"
        + id, Contacto.class);
        System.out.println("Contacto obtenido por ID: " + contacto);
    }

    private void eliminarContacto(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Contacto eliminado con ID: " + id);
    }

    private Contacto modificarContacto(Long id, Contacto contacto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Contacto> requestEntity = new HttpEntity<>(contacto, headers);
        ResponseEntity<Contacto> response =
            restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT,
            requestEntity, Contacto.class);

        return response.getBody();
    }
}
