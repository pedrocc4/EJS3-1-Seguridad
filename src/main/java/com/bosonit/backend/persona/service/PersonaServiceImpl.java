package com.bosonit.backend.persona.service;

import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.domain.exceptions.PersonaNoEncontrada;
import com.bosonit.backend.persona.domain.exceptions.PersonaYaRegistrada;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepositoryJPA repository;

    @Override
    public int addPersona(Persona persona) throws PersonaYaRegistrada {
        /* EJEMPLO: comprobar si existe una persona (lioso y no efectivo en derterminados casos */
//        ExampleMatcher modelMatcher = ExampleMatcher.matching()
//                .withIgnorePaths("id")
//                .withMatcher("username", ignoreCase());
//
//        Example<Persona> example = Example.of(persona, modelMatcher);
//
//        if (!repository.exists(example)) {
//            return repository.save(persona).getId();
//        }
//        throw new PersonaYaRegistrada();

        // Comprobacion con previa busqueda (menos eficiente [supongo])

        if (!repository.findByUsername(persona.getUsuario()).isPresent())
            return repository.save(persona).getId();
        throw new PersonaYaRegistrada();
    }

    @Override
    public Persona getPersona(Integer id) throws PersonaNoEncontrada {
        return repository.findById(id).orElseThrow(PersonaNoEncontrada::new);
    }

    @Override
    public Persona getPersonaByUser(String username) throws PersonaNoEncontrada {
        return repository.findByUsername(username).orElseThrow(PersonaNoEncontrada::new);
    }

    @Override
    public List<Persona> getPersonas() {
        return repository.findAll();
    }

    @Override
    public void actPersona(Persona p) throws PersonaNoEncontrada {
        if (!repository.findById(p.getId()).isPresent())
            throw new PersonaNoEncontrada();
        repository.save(p);
    }

    @Override
    public void delPersona(Persona p) throws PersonaNoEncontrada {
        if (!repository.findById(p.getId()).isPresent())
            throw new PersonaNoEncontrada();
        repository.delete(p);
    }
}
