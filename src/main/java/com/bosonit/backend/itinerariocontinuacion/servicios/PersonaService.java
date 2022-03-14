package com.bosonit.backend.itinerariocontinuacion.servicios;

import com.bosonit.backend.itinerariocontinuacion.entidades.Persona;

import javax.validation.Valid;
import java.util.List;

public interface PersonaService {
    int addPersona(@Valid Persona p);

    Persona getPersona(Integer id);

    List<Persona> getPersonas();

    Persona getPersonaByUser(String username);

    void actPersona(@Valid Persona p);

    void delPersona(@Valid Persona p);
}
