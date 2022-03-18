package com.bosonit.backend.profesor.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.profesor.domain.Profesor;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.mapper.ProfesorMapper;
import com.bosonit.backend.profesor.repository.ProfesorRepositoryJPA;
import com.bosonit.backend.utils.exceptions.ProfesorNoEncontrado;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepositoryJPA repository;

    @Autowired
    private ProfesorMapper mapper;

    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) {
        try {
            return mapper.toDTO(repository.save(mapper.toEntity(profesorInputDTO)));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public List<EstudianteOutputDTO> addEstudiantes() {
        return null;
    }

    @Override
    public ProfesorOutputDTO getProfesor(String id) {
        return mapper.toDTO(repository.findById(id)
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
}
