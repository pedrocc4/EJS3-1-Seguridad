package com.bosonit.backend.estudiante_asignatura.service;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import com.bosonit.backend.estudiante.domain.Estudiante;

import java.util.List;

public interface Estudiante_AsignaturaService {
    void addEstudiante_Asignatura(Estudiante estudiante, Asignatura asignatura);
    List<AsignaturaOutputDTO> getAsignaturas(String id);
}
