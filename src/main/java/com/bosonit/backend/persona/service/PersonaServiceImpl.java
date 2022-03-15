package com.bosonit.backend.persona.service;

import com.bosonit.backend.persona.domain.exceptions.PersonaNoEncontrada;
import com.bosonit.backend.persona.domain.exceptions.PersonaYaRegistrada;
import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.infrastructure.controller.mapper.PersonaMapper;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepositoryJPA repository;

    @Autowired
    private PersonaMapper mapper;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws PersonaYaRegistrada {
        /* EJEMPLO: comprobar si existe una persona (lioso y no efectivo en determinados casos */
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

        if (!repository.findByUsername(personaInputDTO.getUsuario()).isPresent())
            return mapper.toDTO(repository.save(mapper.toEntity(personaInputDTO)));
        throw new PersonaYaRegistrada();
    }

    @Override
    public PersonaOutputDTO getPersona(Integer id) throws PersonaNoEncontrada {
        return mapper.toDTO(repository.findById(id).orElseThrow(PersonaNoEncontrada::new));
    }

    @Override
    public PersonaOutputDTO getPersonaByUser(String username) throws PersonaNoEncontrada {
        return mapper.toDTO(repository.findByUsername(username).orElseThrow(PersonaNoEncontrada::new));
    }

    @Override
    public List<PersonaOutputDTO> getPersonas() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public void actPersona(int id, PersonaInputDTO personaInputDTO) throws PersonaNoEncontrada {
        if (!repository.findById(id).isPresent())
            throw new PersonaNoEncontrada();

        repository.save(mapper.toEntity(personaInputDTO));
    }

    @Override
    public void delPersona(int id) throws PersonaNoEncontrada {
        if (!repository.findById(id).isPresent())
            throw new PersonaNoEncontrada();
        repository.delete((repository.findById(id).orElseThrow(PersonaNoEncontrada::new)));
    }
}
