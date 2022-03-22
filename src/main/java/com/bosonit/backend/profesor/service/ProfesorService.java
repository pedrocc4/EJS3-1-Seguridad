package com.bosonit.backend.profesor.service;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO);

    ProfesorOutputDTO addEstudiantes(String id, List<String> ids_estudiante);

    ProfesorOutputDTO getProfesor(String id);

    ProfesorPersonaOutputDTO getProfesor1(String id);

    ProfesorOutputDTO actProfesor(String id, ProfesorInputDTO profesorInputDTO);

    void delProfesor(String id);

    List<ProfesorOutputDTO> getProfesores();

    List<ProfesorPersonaOutputDTO> getProfesores1();

    ProfesorPersonaOutputDTO addPersona(String id_profesor, int id_persona);

    List<EstudianteOutputDTO>  getEstudiantes(String id);
}
