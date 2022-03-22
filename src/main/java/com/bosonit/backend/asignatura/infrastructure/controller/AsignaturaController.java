package com.bosonit.backend.asignatura.infrastructure.controller;

import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaInputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import com.bosonit.backend.asignatura.service.AsignaturaService;
import com.bosonit.backend.utils.exceptions.AsignaturaNoEncontrada;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AsignaturaController {
    @Autowired
    private AsignaturaService service;

    @PostMapping("asignatura")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<AsignaturaOutputDTO> addAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addAsignatura(asignaturaInputDTO));
        } catch (UnprocesableException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", e);
        }
    }

    @GetMapping("asignatura/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<AsignaturaOutputDTO> getAsignatura(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAsignatura(id));
        } catch (AsignaturaNoEncontrada e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
    }

    @PutMapping("asignatura/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<AsignaturaOutputDTO> actAsignatura(
            @PathVariable String id,
            @RequestBody AsignaturaInputDTO asignaturaInputDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.actAsignatura(id, asignaturaInputDTO));
        } catch (AsignaturaNoEncontrada e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        } catch (UnprocesableException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", e);
        }
    }
}
