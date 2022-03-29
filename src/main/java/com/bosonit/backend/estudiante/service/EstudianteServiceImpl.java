package com.bosonit.backend.estudiante.service;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.asignatura.repository.AsignaturaRepositoryJPA;
import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.mapper.EstudianteMapper;
import com.bosonit.backend.estudiante.repository.EstudianteRepositoryJPA;
import com.bosonit.backend.estudiante_asignatura.service.Estudiante_AsignaturaService;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.persona.repository.PersonaRepositoryJPA;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.mapper.ProfesorMapper;
import com.bosonit.backend.utils.exceptions.ConstraintViolationException;
import com.bosonit.backend.utils.exceptions.EntidadNoEncontrada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepositoryJPA repository;

    @Autowired
    private EstudianteMapper mapper;

    @Autowired
    private PersonaRepositoryJPA personaRepository;

    @Autowired
    private AsignaturaRepositoryJPA asignaturaRepository;

    @Autowired
    private Estudiante_AsignaturaService service;

    @Autowired
    private ProfesorMapper profesorMapper;

    @Override
    public EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO)
            throws ConstraintViolationException {
        Estudiante estudiante = mapper.toEntity(estudianteInputDTO);

        //comprobamos si id_persona esta asignado
        if (estudiante.getId_persona() != null) {
            Persona persona = personaRepository.findById(estudianteInputDTO.getId_persona().getId())
                    .orElseThrow(
                            () -> new EntidadNoEncontrada(
                                    "Persona con id: "
                                            + estudianteInputDTO.getId_persona()
                                            + ", no encontrada"));

            if (persona.getTipoPersona() != null) {
                persona.setTipoPersona(Persona.TipoPersona.ESTUDIANTE);
                personaRepository.save(persona);
                estudiante.setId_persona(persona);
            }
        }

        // es posible que no se le asigne una persona a estudiante, en esa casuistica
        // se haria de forma manual con el metodo Estudiante::addPersona
        return mapper.toDTO(repository.save(estudiante));
    }

    @Override
    public EstudianteOutputDTO getEstudiante(String id)
            throws EntidadNoEncontrada {
        return mapper.toDTO(repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada
                        ("Estudiante con id: " + id + ", no encontrado")));
    }

    @Override
    public EstudiantePersonaOutputDTO getEstudiante2(String id)
            throws EntidadNoEncontrada {
        return mapper.toDTO2(repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada
                        ("Estudiante con id: " + id + ", no encontrado")));
    }

    @Override
    public EstudianteOutputDTO actEstudiante(String id, EstudianteInputDTO estudianteInputDTO)
            throws ConstraintViolationException, EntidadNoEncontrada {
        Estudiante estudiante =
                repository
                        .findById(id)
                        .orElseThrow(() ->
                                new EntidadNoEncontrada(
                                        "Estudiante con id: " + id + ", no encontrado"));

        // Asignacion de nuevos atributos
        BeanUtils.copyProperties(estudianteInputDTO, estudiante);
        return mapper.toDTO(repository.save(estudiante));
    }

    @Override
    public void delEstudiante(String id) {
        repository.delete((repository
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada
                        ("Estudiante con id: " + id + ", no encontrado"))));
    }

    @Override
    public List<EstudianteOutputDTO> getEstudiantes() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public List<EstudiantePersonaOutputDTO> getEstudiantes1() {
        return mapper.toDTOList1(repository.findAll());
    }

    @Override
    public EstudianteOutputDTO addAsignaturas(String id, List<String> idsAsignaturas)
            throws ConstraintViolationException {

        Estudiante estudiante = repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada(
                        "Estudiante con id: " + id + ", no encontrado"));

        List<Asignatura> asignaturas;
        List<Asignatura> list = new ArrayList<>();
        for (String idsAsignatura : idsAsignaturas) {
            Asignatura orElseThrow = asignaturaRepository
                    .findById(idsAsignatura)
                    .orElseThrow(() -> new EntidadNoEncontrada(
                            "Asignatura con id: " + id + ", no encontrada"));
            list.add(orElseThrow);
        }
        asignaturas = list;

        //estudiante.setAsignaturas(asignaturas);
        // no asignamos a la entidad estudiante -> asignaturas, asignamos en la tabla intermedia
        // y viceversa

        asignaturas.forEach(asignatura -> service.addEstudiante_Asignatura(estudiante, asignatura));

        return mapper.toDTO(repository.save(estudiante));
    }

    @Override
    public EstudiantePersonaOutputDTO addPersona(String id_estudiante, int id_persona) {
        Persona persona = personaRepository.findById(id_persona)
                .orElseThrow(() -> new EntidadNoEncontrada("Persona con id: " + id_persona + ", no encontrada"));

        Estudiante estudiante = repository.findById(id_estudiante)
                .orElseThrow(() -> new EntidadNoEncontrada("Estudiante con id: " + id_estudiante + ", no encontrado"));

        estudiante.setId_persona(persona);
        return mapper.toDTO2(repository.save(estudiante));
    }

    @Override
    public ProfesorOutputDTO getProfesor(String id) {
        Estudiante estudiante = repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Estudiante con id: " + id + ", no encontrado"));

        return profesorMapper.toDTO(repository.getProfesor(estudiante)
                .orElseThrow(() -> new EntidadNoEncontrada("Estudiante con id: " + id + ", no tiene profesor asignado")));
    }
}
