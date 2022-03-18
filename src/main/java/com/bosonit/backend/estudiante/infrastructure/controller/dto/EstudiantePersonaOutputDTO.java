package com.bosonit.backend.estudiante.infrastructure.controller.dto;

import com.bosonit.backend.persona.domain.Persona;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class EstudiantePersonaOutputDTO extends EstudianteOutputDTO {
    private Persona id_persona;
}
