package com.bosonit.backend.profesor.service;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.mapper.EstudianteMapper;
import com.bosonit.backend.estudiante.repository.EstudianteRepositoryJPA;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import com.bosonit.backend.profesor.domain.Profesor;
import com.bosonit.backend.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.mapper.ProfesorMapper;
import com.bosonit.backend.profesor.repository.ProfesorRepositoryJPA;
import com.bosonit.backend.utils.exceptions.ConstraintViolationException;
import com.bosonit.backend.utils.exceptions.EntidadNoEncontrada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO)
            throws ConstraintViolationException {
        Profesor profesor = mapper.toEntity(profesorInputDTO);

        //comprobamos si id_persona esta asignado
        if (profesor.getId_persona() != null) {
            Persona persona = personaRepository.findById(profesorInputDTO.getId_persona().getId())
                    .orElseThrow(
                            () -> new EntidadNoEncontrada(
                                    "Persona con id: "
                                            + profesorInputDTO.getId_persona()
                                            + ", no encontrada"));

            if (persona.getTipoPersona() == null) {
                persona.setTipoPersona(Persona.TipoPersona.PROFESOR);
                log.info(String.valueOf(personaRepository.save(persona)));
                profesor.setId_persona(persona);

            }
        }

        // es posible que no se le asigne una persona a profesor, en esa casuistica
        // se haria de forma manual con el metodo Profesor::addPersona
        return mapper.toDTO(repository.save(mapper.toEntity(profesorInputDTO)));
    }

    @Override
    public ProfesorOutputDTO addEstudiantes(String id, List<String> ids_estudiantes) {
        Profesor profesor =
                repository.findById(id).orElseThrow(
                        () -> new EntidadNoEncontrada("Profesor con id: " + id + ", no encontrado"));
        List<Estudiante> estudiantes = ids_estudiantes.stream()
                .map(ids_estudiante -> estudianteRepository
                        .findById(ids_estudiante)
                        .orElseThrow(() -> new EntidadNoEncontrada(
                                "Estudiante con id: " + ids_estudiante + ", no encontrado")))
                .collect(Collectors.toList());
        profesor.setEstudiantes(estudiantes);
        return mapper.toDTO(repository.save(profesor));
    }

    @Override
    public ProfesorOutputDTO getProfesor(String id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Profesor con id: " + id + ", no encontrado")));
    }

    @Override
    public ProfesorPersonaOutputDTO getProfesor1(String id) {
        return mapper.toDTO1(repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Profesor con id: " + id + ", no encontrado")));
    }

    @Override
    public ProfesorOutputDTO actProfesor(String id, ProfesorInputDTO profesorInputDTO)
            throws ConstraintViolationException {
        Profesor profesor = repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Profesor con id: " + id + ", no encontrado"));

        BeanUtils.copyProperties(profesorInputDTO, profesor);
        return mapper.toDTO(repository.save(profesor));

    }

    @Override
    public void delProfesor(String id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Profesor con id: " + id + ", no encontrado")));
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
                .orElseThrow(() -> new EntidadNoEncontrada("Profesor con id: " + id_profesor + ", no encontrado"));

        Persona persona = personaRepository.findById(id_persona)
                .orElseThrow(() -> new EntidadNoEncontrada("Persona con id: " + id_persona + ", no encontrada"));

        profesor.setId_persona(persona);
        return mapper.toDTO1(repository.save(profesor));
    }

    @Override
    public List<EstudianteOutputDTO> getEstudiantes(String id) {
        return estudianteMapper.toDTOList(repository.getEstudiantes(id));
    }
}
