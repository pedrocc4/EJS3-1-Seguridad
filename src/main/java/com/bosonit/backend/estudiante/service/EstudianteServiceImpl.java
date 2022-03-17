package com.bosonit.backend.estudiante.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.mapper.EstudianteMapper;
import com.bosonit.backend.estudiante.infrastructure.exceptions.EstudianteNoEncontrado;
import com.bosonit.backend.estudiante.repository.EstudianteRepositoryJPA;
import com.bosonit.backend.persona.infrastructure.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepositoryJPA repository;

    @Autowired
    private EstudianteMapper mapper;

    @Override
    public EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO)
            throws UnprocesableException {
        try {
            return mapper.toDTO(
                    repository.save(mapper.toEntity(estudianteInputDTO)));
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
    public EstudianteOutputDTO actEstudiante(String id, EstudianteInputDTO estudianteInputDTO)
            throws EstudianteNoEncontrado, UnprocesableException {
        if (repository.findById(id).isEmpty())
            throw new EstudianteNoEncontrado("Estudiante con id: " + id + ", no encontrado");

        try {
            estudianteInputDTO.setId_estudiante(id);
            return mapper.toDTO(repository.save(mapper.toEntity(estudianteInputDTO)));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }


    @Override
    public void delEstudiante(String id) {

    }

    @Override
    public List<EstudianteOutputDTO> getEstudiantes() {
        return null;
    }
}
