package com.bosonit.backend.estudiante.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudiantePersonaOutputDTO;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public interface EstudianteService {
    EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO);

    EstudianteOutputDTO getEstudiante(String id);

    EstudiantePersonaOutputDTO getEstudiante2(String id);

    EstudianteOutputDTO actEstudiante(String id, EstudianteInputDTO estudianteInputDTO) throws Exception;

    void delEstudiante(String id);

    List<EstudianteOutputDTO> getEstudiantes();
}
