package com.bosonit.backend.itinerariocontinuacion.REST;

import com.bosonit.backend.itinerariocontinuacion.entidades.Persona;
import com.bosonit.backend.itinerariocontinuacion.servicios.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class Controlador {
    //TODO Implementar con DTOs

    @Autowired
    private PersonaService service;

    @GetMapping("personas/{id}")
    public Persona getPersona(@PathVariable Integer id) {
        log.info("Buscando persona con id: " + id);
        return service.getPersona(id);
    }

    @GetMapping("personas")
    public List<Persona> getPersonas() {
        log.info("Mostrando todas las personas");
        return service.getPersonas();
    }

    @GetMapping("personas/{username}")
    public Persona getPersona(@RequestParam String username) {
        log.info("Buscando persona con nombre: " + username);
        return service.getPersonaByUser(username);
    }

    @PostMapping("personas")
    public Integer addPersona(@RequestBody Persona p) {
        log.info("Agregando: " + p);
        return service.addPersona(p);
    }

    @PutMapping("personas")
    public void actPersona(@RequestBody Persona p) {
        log.info("Actualizando: " + p);
        service.actPersona(p);
    }

    @DeleteMapping("personas")
    public void delPersona(@RequestBody Persona p) {
        log.info("Borrando: " + p);
        service.delPersona(p);
    }
}
