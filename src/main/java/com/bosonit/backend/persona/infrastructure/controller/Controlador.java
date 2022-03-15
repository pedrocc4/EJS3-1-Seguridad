package com.bosonit.backend.persona.infrastructure.controller;

import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class Controlador {

    @Autowired
    private PersonaService service;

    @PostMapping("persona")
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO) {
        log.info("Agregando: " + personaInputDTO);
        return service.addPersona(personaInputDTO);
    }

    @GetMapping("persona/{id}")
    public PersonaOutputDTO getPersona(@PathVariable int id) {
        log.info("Buscando persona con id: " + id);
        return service.getPersona(id);
    }

    @GetMapping("personas")
    public List<PersonaOutputDTO> getPersonas() {
        log.info("Mostrando todas las personas");
        return service.getPersonas();
    }

    @GetMapping("persona")
    public PersonaOutputDTO getPersona(@RequestParam String username) {
        log.info("Buscando persona con nombre: " + username);
        return service.getPersonaByUser(username);
    }

    @PutMapping("persona/{id}")
    public void actPersona(@PathVariable int id, @RequestBody PersonaInputDTO personaInputDTO) {
        log.info("Actualizando: " + personaInputDTO);
        service.actPersona(id, personaInputDTO);
    }

    @DeleteMapping("persona/{id}")
    public void delPersona(@PathVariable int id) {
        log.info("Borrando persona con id: " + id);
        service.delPersona(id);
    }
}
