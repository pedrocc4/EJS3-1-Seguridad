package com.bosonit.backend.profesor.infrastructure.controller.dto;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorInputDTO {
    private String comments;
    private String branch;
    private Persona id_persona;
    private List<ProfesorInputDTO> estudiantes;
}
