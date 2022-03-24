package com.bosonit.backend.estudiante.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;

import java.util.List;

public interface EstudianteService {
    EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO);

    EstudianteOutputDTO getEstudiante(String id);

    EstudiantePersonaOutputDTO getEstudiante2(String id);

    EstudianteOutputDTO actEstudiante(String id, EstudianteInputDTO estudianteInputDTO) throws Exception;

    void delEstudiante(String id);

    List<EstudianteOutputDTO> getEstudiantes();

    List<EstudiantePersonaOutputDTO> getEstudiantes1();

    EstudianteOutputDTO addAsignaturas(String id, List<String> idsAsignaturas);

    EstudiantePersonaOutputDTO addPersona(String id_estudiante, int id_persona);

    ProfesorOutputDTO getProfesor(String id);
}
