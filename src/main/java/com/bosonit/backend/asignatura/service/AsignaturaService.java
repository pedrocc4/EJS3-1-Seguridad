package com.bosonit.backend.asignatura.service;

import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaInputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO);

    AsignaturaOutputDTO getAsignatura(String id);

    AsignaturaOutputDTO actAsignatura(String id, AsignaturaInputDTO asignaturaInputDTO);

    void delAsignatura(String id);

    List<AsignaturaOutputDTO> getAsignaturas();
}
