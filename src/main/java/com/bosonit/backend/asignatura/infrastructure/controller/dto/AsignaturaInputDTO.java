package com.bosonit.backend.asignatura.infrastructure.controller.dto;

import com.bosonit.backend.estudiante.domain.Estudiante;
import lombok.Data;

import java.util.List;

@Data
public class AsignaturaInputDTO {
    private String nombre;
    private String descripcion;
    private List<Estudiante> estudiantes;
}
