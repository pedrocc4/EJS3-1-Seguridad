package com.bosonit.backend.profesor.infrastructure.controller.dto.output;

import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfesorPersonaOutputDTO extends ProfesorOutputDTO {
    private Persona id_persona;
}
