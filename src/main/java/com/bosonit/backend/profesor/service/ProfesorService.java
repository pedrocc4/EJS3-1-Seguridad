package com.bosonit.backend.profesor.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorOutputDTO;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO);

    ProfesorOutputDTO addEstudiantes(String id, List<String> ids_estudiante);

    ProfesorOutputDTO getProfesor(String id);

    ProfesorOutputDTO actProfesor(String id, ProfesorInputDTO profesorInputDTO);

    void delProfesor(String id);

    List<ProfesorOutputDTO> getProfesores();
}
