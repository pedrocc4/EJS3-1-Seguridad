package com.bosonit.backend.itinerariocontinuacion.servicios;

import com.bosonit.backend.itinerariocontinuacion.entidades.Persona;
import com.bosonit.backend.itinerariocontinuacion.excepciones.PersonaNoEncontrada;
import com.bosonit.backend.itinerariocontinuacion.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository repository;

    @Override
    public int addPersona(Persona p) {
        return repository.save(p).getId();
    }

    @Override
    public Persona getPersona(Integer id) {
        return repository.findById(id).orElseThrow(PersonaNoEncontrada::new);
    }

    @Override
    public Persona getPersonaByUser(String username) {
        return repository.findAllUsername(username).orElseThrow(PersonaNoEncontrada::new);
    }

    @Override
    public List<Persona> getPersonas() {
        return repository.findAll();
    }

    @Override
    public void actPersona(Persona p) {
        repository.save(p);
    }

    @Override
    public void delPersona(Persona p) {
        repository.delete(p);
    }
}
