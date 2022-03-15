package com.bosonit.backend.persona.service;

import com.bosonit.backend.persona.domain.Persona;

import javax.validation.Valid;
import java.util.List;

public interface PersonaService {
    int addPersona(@Valid Persona persona);

    Persona getPersona(Integer id);

    List<Persona> getPersonas();

    Persona getPersonaByUser(String username);

    void actPersona(@Valid Persona persona);

    void delPersona(@Valid Persona persona);
}
