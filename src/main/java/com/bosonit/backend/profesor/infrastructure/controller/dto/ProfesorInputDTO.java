package com.bosonit.backend.profesor.infrastructure.controller.dto;

import com.bosonit.backend.estudiante.domain.Estudiante;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorInputDTO {
    private String comments;
    private String branch;
    private List<ProfesorInputDTO> estudiantes;
}
