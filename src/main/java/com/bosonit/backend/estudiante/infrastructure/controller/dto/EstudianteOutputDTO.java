package com.bosonit.backend.estudiante.infrastructure.controller.dto;

import com.bosonit.backend.asignatura.domain.Asignatura;
import lombok.Data;

import java.util.List;

@Data
public class EstudianteOutputDTO {
    private String id_estudiante;
    private int num_hours_week;
    private String comments;
    private String branch;
    private List<Asignatura> asignaturas;
}
