package com.bosonit.backend.profesor.service;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante.repository.EstudianteRepositoryJPA;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import com.bosonit.backend.profesor.domain.Profesor;
import com.bosonit.backend.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.mapper.ProfesorMapper;
import com.bosonit.backend.profesor.repository.ProfesorRepositoryJPA;
import com.bosonit.backend.utils.exceptions.EstudianteNoEncontrado;
import com.bosonit.backend.utils.exceptions.PersonaNoEncontrada;
import com.bosonit.backend.utils.exceptions.ProfesorNoEncontrado;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepositoryJPA repository;

    @Autowired
    private ProfesorMapper mapper;

    @Autowired
    private EstudianteRepositoryJPA estudianteRepository;

    @Autowired
    private PersonaRepositoryJPA personaRepository;

    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) {
        try {//FIXME que pasa si agregamos estudiantes
            Profesor profesor = mapper.toEntity(profesorInputDTO);
//            Persona persona =
//                    personaRepository.findById(profesorInputDTO.getId_persona().getId())
//                            .orElseThrow(
//                                    () -> new PersonaNoEncontrada(
//                                            "Persona con id: "
//                                                    + profesorInputDTO.getId_persona()
//                                                    + ", no encontrada"));
            //profesor.setId_persona(persona);
            //persona.setTipoPersona(Persona.TipoPersona.PROFESOR);
            //personaRepository.save(persona);
            return mapper.toDTO(repository.save(mapper.toEntity(profesorInputDTO)));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public ProfesorOutputDTO addEstudiantes(String id, List<String> ids_estudiantes) {
        Profesor profesor =
                repository.findById(id).orElseThrow(
                        () -> new ProfesorNoEncontrado("Profesor con id: " + id + ", no encontrado"));
        List<Estudiante> estudiantes = ids_estudiantes.stream()
                .map(ids_estudiante -> estudianteRepository
                        .findById(ids_estudiante)
                        .orElseThrow(() -> new EstudianteNoEncontrado(
                                "Estudiante con id: " + ids_estudiante + ", no encontrado")))
                .collect(Collectors.toList());
        profesor.setEstudiantes(estudiantes);
        return mapper.toDTO(repository.save(profesor));
    }

    @Override
    public ProfesorOutputDTO getProfesor(String id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new ProfesorNoEncontrado("Profesor con id: " + id + ", no encontrado")));
    }

    @Override
    public ProfesorPersonaOutputDTO getProfesor1(String id) {
        return mapper.toDTO1(repository.findById(id)
                .orElseThrow(() -> new ProfesorNoEncontrado("Profesor con id: " + id + ", no encontrado")));
    }

    @Override
    public ProfesorOutputDTO actProfesor(String id, ProfesorInputDTO profesorInputDTO) {
        try {
            Profesor profesor = repository.findById(id)
                    .orElseThrow(() -> new ProfesorNoEncontrado("Profesor con id: " + id + ", no encontrado"));

            BeanUtils.copyProperties(profesorInputDTO, profesor);
            return mapper.toDTO(repository.save(profesor));
        } catch (ConstraintViolationException c) {
            throw new UnprocesableException(c.getMessage());
        }
    }

    @Override
    public void delProfesor(String id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new ProfesorNoEncontrado("Profesor con id: " + id + ", no encontrado")));
    }

    @Override
    public List<ProfesorOutputDTO> getProfesores() {
        return mapper.toDTOList(repository.findAll());
    }

    public List<ProfesorPersonaOutputDTO> getProfesores1() {
        return mapper.toDTOList1(repository.findAll());
    }


    @Override
    public ProfesorPersonaOutputDTO addPersona(String id_profesor, int id_persona) {
        Profesor profesor = repository.findById(id_profesor)
                .orElseThrow(() -> new ProfesorNoEncontrado("Profesor con id: " + id_profesor + ", no encontrado"));

        Persona persona = personaRepository.findById(id_persona)
                .orElseThrow(() -> new ProfesorNoEncontrado("Persona con id: " + id_persona + ", no encontrada"));

        profesor.setId_persona(persona);

        return mapper.toDTO1(repository.save(profesor));
    }
}
