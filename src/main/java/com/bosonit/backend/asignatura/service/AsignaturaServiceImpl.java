package com.bosonit.backend.asignatura.service;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaInputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.mapper.AsignaturaMapper;
import com.bosonit.backend.asignatura.repository.AsignaturaRepositoryJPA;
import com.bosonit.backend.utils.exceptions.ConstraintViolationException;
import com.bosonit.backend.utils.exceptions.EntidadNoEncontrada;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepositoryJPA repository;

    @Autowired
    private AsignaturaMapper mapper;


    @Override
    public AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO) throws ConstraintViolationException {
        return mapper.toDTO(repository.save(mapper.toEntity(asignaturaInputDTO)));
    }

    @Override
    public AsignaturaOutputDTO getAsignatura(String id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(
                () -> new EntidadNoEncontrada("Asignatura con id: " + id + ", no encontrada")));
    }

    @Override
    public AsignaturaOutputDTO actAsignatura(String id, AsignaturaInputDTO asignaturaInputDTO)
            throws ConstraintViolationException {
        Asignatura asignatura = repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Asignatura con id: " + id + ", no encontrada"));

        // Asignacion de nuevos atributos
        BeanUtils.copyProperties(asignaturaInputDTO, asignatura);
        return mapper.toDTO(repository.save(asignatura));

    }

    @Override
    public void delAsignatura(String id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontrada("Asignatura con id: " + id + ", no encontrada")));
    }

    @Override
    public List<AsignaturaOutputDTO> getAsignaturas() {
        return mapper.toDTOList(repository.findAll());
    }
}
