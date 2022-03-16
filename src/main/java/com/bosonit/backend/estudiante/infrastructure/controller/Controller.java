package com.bosonit.backend.estudiante.infrastructure.controller;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.service.EstudianteService;
import com.bosonit.backend.persona.infrastructure.exceptions.UnprocesableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class Controller {
    @Autowired
    private EstudianteService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("estudiante")
    public ResponseEntity<EstudianteOutputDTO> addEstudiante(
            @RequestBody EstudianteInputDTO estudianteInputDTO) {
        log.info("Intentando agregar a: " + estudianteInputDTO);
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.addEstudiante(estudianteInputDTO));
        } catch (UnprocesableException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", e);
        }
    }
}
