package com.bosonit.backend.estudiante_asignatura.infrastructure.controller;

import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import com.bosonit.backend.estudiante_asignatura.service.Estudiante_AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Estudiante_AsignaturaController {
    @Autowired
    Estudiante_AsignaturaService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/estudiante/{id}/asignaturas")
    public ResponseEntity<List<AsignaturaOutputDTO>> getEstudianteAsignaturas(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAsignaturas(id));
    }
}
