package com.bosonit.backend.asignatura.service;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaInputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.mapper.AsignaturaMapper;
import com.bosonit.backend.asignatura.repository.AsignaturaRepositoryJPA;
import com.bosonit.backend.utils.exceptions.EntidadNoEncontrada;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepositoryJPA repository;
    @Autowired
    private AsignaturaMapper mapper;


    @Override
    public AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO) {
        try {
            return mapper.toDTO(repository.save(mapper.toEntity(asignaturaInputDTO)));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public AsignaturaOutputDTO getAsignatura(String id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(
                () -> new EntidadNoEncontrada("Asignatura con id: " + id + ", no encontrada")));
    }

    @Override
    public AsignaturaOutputDTO actAsignatura(String id, AsignaturaInputDTO asignaturaInputDTO) {
        Asignatura asignatura = repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Asignatura con id: " + id + ", no encontrada"));

        BeanUtils.copyProperties(asignaturaInputDTO, asignatura);
        try {
            return mapper.toDTO(repository.save(asignatura));
        } catch (ConstraintViolationException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @Override
    public void delAsignatura(String id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Asignatura con id: " + id + ", no encontrada")));
    }

    @Override
    public List<AsignaturaOutputDTO> getAsignaturas() {
        return null;
    }
}
