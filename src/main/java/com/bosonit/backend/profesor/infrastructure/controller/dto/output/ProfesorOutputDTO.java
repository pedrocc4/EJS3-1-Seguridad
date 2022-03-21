package com.bosonit.backend.profesor.infrastructure.controller.dto.output;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorOutputDTO {
    private String id_profesor;
    private String comments;
    private String branch;
    private List<EstudianteInputDTO> estudiantes;
}
