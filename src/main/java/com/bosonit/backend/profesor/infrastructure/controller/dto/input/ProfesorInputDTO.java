package com.bosonit.backend.profesor.infrastructure.controller.dto.input;

import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorInputDTO {
    private String comments;
    private String branch;
    private List<ProfesorInputDTO> estudiantes;
    private Persona id_persona;
}
