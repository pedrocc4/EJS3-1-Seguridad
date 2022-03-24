package com.bosonit.backend.persona.service;

import com.bosonit.backend.estudiante.infrastructure.controller.mapper.EstudianteMapper;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.infrastructure.controller.mapper.PersonaMapper;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import com.bosonit.backend.profesor.infrastructure.controller.mapper.ProfesorMapper;
import com.bosonit.backend.utils.exceptions.ConstraintViolationException;
import com.bosonit.backend.utils.exceptions.EntidadNoEncontrada;
import com.bosonit.backend.utils.exceptions.PersonaYaRegistrada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepositoryJPA repository;

    @Autowired
    private PersonaMapper mapper;

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Autowired
    private ProfesorMapper profesorMapper;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws ConstraintViolationException {
        if (repository.findByUsername(personaInputDTO.getUsuario()).isPresent())
            throw new PersonaYaRegistrada("Usuario ya registrado");
        return mapper.toDTO(repository.save(mapper.toEntity(personaInputDTO)));
    }

    @Override
    public Object getPersona(Integer id, String outputType) {
        Persona persona = repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada(
                        ("Persona con id: " + id + ", no encontrado")));

        if (outputType.equals("full")) {
            log.info("Aqui soi no?=");
            if (persona.getTipoPersona().equals(Persona.TipoPersona.ESTUDIANTE)) {
                return estudianteMapper.toDTO2(repository.getEstudiante(persona)
                        .orElseThrow(() -> new EntidadNoEncontrada("Fallo al asignar estudiante")));

            } else if (persona.getTipoPersona().equals(Persona.TipoPersona.PROFESOR)) {
                return profesorMapper.toDTO1(repository.getProfesor(persona)
                        .orElseThrow(() -> new EntidadNoEncontrada("Fallo al asignar profesor")));
            }
        }

        return mapper.toDTO(persona);
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
    public void actPersona(int id, PersonaInputDTO personaInputDTO) throws ConstraintViolationException {
        Persona persona =
                repository.findById(id)
                        .orElseThrow(() ->
                                new EntidadNoEncontrada("Persona con id: " + id + ", no encontrada"));

        // Asignacion de nuevos atributos
        BeanUtils.copyProperties(personaInputDTO, persona);
        repository.save(persona);
    }

    @Override
    public void delPersona(int id) {
        repository.delete((repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada(
                        ("Persona con id: " + id + ", no encontrado")))));
    }
}
