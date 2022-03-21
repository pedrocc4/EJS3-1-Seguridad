package com.bosonit.backend.estudiante_asignatura.infrastructure.controller.dto;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.estudiante.domain.Estudiante;

import java.util.Date;

public class Estudiante_AsignaturaInputDTO {

    private String comments;
    private Date initial_date;
    private Date finish_date;
    private Estudiante id_estudiante;
    private Asignatura id_asignatura;
}
