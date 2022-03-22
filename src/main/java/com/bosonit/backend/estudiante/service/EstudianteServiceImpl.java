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
import com.bosonit.backend.utils.exceptions.AsignaturaNoEncontrada;
import com.bosonit.backend.utils.exceptions.EstudianteNoEncontrado;
import com.bosonit.backend.utils.exceptions.PersonaNoEncontrada;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
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

    @Override
    public EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO)
            throws UnprocesableException {
        try {
            //FIXME tiene que haber otra forma
            Estudiante estudiante = mapper.toEntity(estudianteInputDTO);
            //FIXME comprobar si es estudiante o profe antes
            if (estudiante.getId_persona() != null) {
                Persona persona = personaRepository.findById(estudianteInputDTO.getId_persona().getId())
                        .orElseThrow(
                                () -> new PersonaNoEncontrada(
                                        "Persona con id: "
                                                + estudianteInputDTO.getId_persona()
                                                + ", no encontrada"));
                persona.setTipoPersona(Persona.TipoPersona.ESTUDIANTE);
                personaRepository.save(persona);
                estudiante.setId_persona(persona);
            }
            return mapper.toDTO(
                    repository.save(estudiante));
        } catch (ConstraintViolationException c) {
            throw new UnprocesableException(c.getMessage());
        }
    }

    @Override
    public EstudianteOutputDTO getEstudiante(String id)
            throws EstudianteNoEncontrado {
        return mapper.toDTO(repository
                .findById(id)
                .orElseThrow(() -> new EstudianteNoEncontrado
                        ("Estudiante con id: " + id + ", no encontrado")));
    }

    @Override
    public EstudiantePersonaOutputDTO getEstudiante2(String id)
            throws EstudianteNoEncontrado {
        return mapper.toDTO2(repository
                .findById(id)
                .orElseThrow(() -> new EstudianteNoEncontrado
                        ("Estudiante con id: " + id + ", no encontrado")));
    }

    @Override
    public EstudianteOutputDTO actEstudiante(String id, EstudianteInputDTO estudianteInputDTO)
            throws ConstraintViolationException, EstudianteNoEncontrado {
        try {
            Estudiante estudiante =
                    repository
                            .findById(id)
                            .orElseThrow(() ->
                                    new EstudianteNoEncontrado(
                                            "Estudiante con id: " + id + ", no encontrado"));

            // Asignacion de nuevos atributos
            BeanUtils.copyProperties(estudianteInputDTO, estudiante);
            return mapper.toDTO(repository.save(estudiante));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public void delEstudiante(String id) {
        repository.delete((repository
                .findById(id)
                .orElseThrow(() -> new EstudianteNoEncontrado
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
    public EstudianteOutputDTO addAsignaturas(String id, List<String> idsAsignaturas) {
        try {
            Estudiante estudiante = repository.findById(id)
                    .orElseThrow(() -> new EstudianteNoEncontrado(
                            "Estudiante con id: " + id + ", no encontrado"));

            List<Asignatura> asignaturas = idsAsignaturas
                    .stream()
                    .map(idsAsignatura -> asignaturaRepository
                            .findById(idsAsignatura)
                            .orElseThrow(() -> new AsignaturaNoEncontrada(
                                    "Asignatura con id: " + id + ", no encontrada"))).toList();
            //estudiante.setAsignaturas(asignaturas);
            asignaturas.forEach(asignatura -> service.addEstudiante_Asignatura(estudiante, asignatura));
            // estudiante.setEstudiante_asignatura(asignaturas);
            //FIXME agregar a estudiante, -> asignaturas
            log.info("aqui?");
            return mapper.toDTO(repository.save(estudiante));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public EstudiantePersonaOutputDTO addPersona(String id_estudiante, int id_persona) {
        Persona persona = personaRepository.findById(id_persona)
                .orElseThrow(() -> new PersonaNoEncontrada("Persona con id: " + id_persona + ", no encontrada"));
        Estudiante estudiante = repository.findById(id_estudiante)
                .orElseThrow(() -> new EstudianteNoEncontrado("Estudiante con id: " + id_estudiante + ", no encontrado"));

        estudiante.setId_persona(persona);
        return mapper.toDTO2(repository.save(estudiante));
    }
}
