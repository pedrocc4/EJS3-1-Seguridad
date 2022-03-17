package com.bosonit.backend.profesor.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorOutputDTO;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO);

    List<EstudianteOutputDTO> addEstudiantes();

    ProfesorOutputDTO getProfesor(String id);

    ProfesorOutputDTO actProfesor(String id, ProfesorInputDTO profesorInputDTO);

    void delProfesor(String id);

    List<ProfesorOutputDTO> getProfesores();
}
