package com.bosonit.backend.estudiante.infrastructure.controller.dto.input;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;

import java.util.List;

@Data
public class EstudianteInputDTO {
    private int num_hours_week;
    private String comments;
    private String branch;
    private Persona id_persona;
    private List<Asignatura> asignaturas;
}
