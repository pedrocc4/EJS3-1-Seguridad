package com.bosonit.backend.profesor.infrastructure.controller.dto;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorOutputDTO {
    private String id_profesor;
    private String comments;
    private String branch;
    private List<EstudianteInputDTO> estudiantes;
}
