package com.bosonit.backend.persona.service;

import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.infrastructure.controller.mapper.PersonaMapper;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import com.bosonit.backend.utils.exceptions.EntidadNoEncontrada;
import com.bosonit.backend.utils.exceptions.PersonaYaRegistrada;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepositoryJPA repository;

    @Autowired
    private PersonaMapper mapper;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) {
        if (repository.findByUsername(personaInputDTO.getUsuario()).isPresent())
            throw new PersonaYaRegistrada("Usuario ya registrado");
//        try {
        return mapper.toDTO(repository.save(mapper.toEntity(personaInputDTO)));
//        } catch (ConstraintViolationException e) {
//            throw new UnprocesableException(e.getMessage());
//        }
        //FIXME como hacer sin el catch?
    }

    @Override
    public PersonaOutputDTO getPersona(Integer id) {
        return mapper.toDTO(repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada(
                        ("Persona con id: " + id + ", no encontrado"))));
    }

    @Override
    public PersonaOutputDTO getPersonaByUser(String username) {
        return mapper.toDTO(repository
                .findByUsername(username)
                .orElseThrow(() -> new EntidadNoEncontrada(
                        ("Usuario: " + username + ", no encontrado"))));
    }

    @Override
    public List<PersonaOutputDTO> getPersonas() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public void actPersona(int id, PersonaInputDTO personaInputDTO) {
        try {
            Persona persona =
                    repository
                            .findById(id)
                            .orElseThrow(() ->
                                    new EntidadNoEncontrada(
                                            "Persona con id: " + id + ", no encontrada"));
            // Asignacion de nuevos atributos

            BeanUtils.copyProperties(personaInputDTO, persona);
            repository.save(persona);
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public void delPersona(int id) {
        repository.delete((repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada(
                        ("Persona con id: " + id + ", no encontrado")))));
    }
}
