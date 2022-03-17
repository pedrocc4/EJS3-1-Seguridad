package com.bosonit.backend.estudiante.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;

import java.util.List;

public interface EstudianteService {
    EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO);

    EstudianteOutputDTO getEstudiante(String id);

    EstudianteOutputDTO actEstudiante(String id, EstudianteInputDTO estudianteInputDTO);

    void delEstudiante(String id);

    List<EstudianteOutputDTO> getEstudiantes();
}
