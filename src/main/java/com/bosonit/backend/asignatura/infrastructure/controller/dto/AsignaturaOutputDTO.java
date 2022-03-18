package com.bosonit.backend.asignatura.infrastructure.controller.dto;

import lombok.Data;

@Data
public class AsignaturaOutputDTO {
    private String id_asignatura;
    private String nombre;
    private String descripcion;
}
