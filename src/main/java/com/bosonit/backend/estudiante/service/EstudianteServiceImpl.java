package com.bosonit.backend.estudiante.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.mapper.EstudianteMapper;
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
    public EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) {
        try {
            return mapper.toDTO(
                    repository.save(mapper.toEntity(estudianteInputDTO)));
        } catch (ConstraintViolationException c) {
            throw new UnprocesableException(c.getMessage());
        }

    }

    @Override
    public EstudianteOutputDTO getEstudiante(String id) {
        return null;
    }

    @Override
    public void actEstudiante(String id, EstudianteInputDTO estudianteInputDTO) {

    }

    @Override
    public void delEstudiante(String id) {

    }

    @Override
    public List<EstudianteOutputDTO> getEstudiantes() {
        return null;
    }
}
