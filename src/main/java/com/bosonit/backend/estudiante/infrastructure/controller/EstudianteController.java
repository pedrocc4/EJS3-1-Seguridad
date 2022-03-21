package com.bosonit.backend.estudiante.infrastructure.controller;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.service.EstudianteService;
import com.bosonit.backend.utils.exceptions.EstudianteNoEncontrado;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class EstudianteController {
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("estudiante/{id}")
    public ResponseEntity<Object> getEstudiante(
            @PathVariable String id,
            @RequestParam(defaultValue = "simple") String outputType) {
        log.info("Intentando buscar estudiante con id: " + id);
        log.info("Valor outputType: " + outputType);
        try {
            if (outputType.equals("simple"))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(service.getEstudiante(id));
            else if (outputType.equals("full"))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(service.getEstudiante2(id));
        } catch (EstudianteNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("estudiante/{id}")
    //@PatchMapping -- para un campo en concreto
    public ResponseEntity<EstudianteOutputDTO> actEstudiante(
            @PathVariable String id,
            @RequestBody EstudianteInputDTO estudianteInputDTO
    ) throws Exception {
        log.info("Intentando actualizar estudiante con id: " + id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.actEstudiante(id, estudianteInputDTO));
        } catch (
                EstudianteNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        } catch (
                UnprocesableException u) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", u);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("estudiante/{id}")
    public ResponseEntity<Void> delEstudiante(@PathVariable String id) {
        log.info("Intentando borrar estudiante con id: " + id);
        try {
            service.delEstudiante(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EstudianteNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("estudiante/{id}/asignaturas") // FIXME post? put? patch?
    public ResponseEntity<EstudianteOutputDTO> addAsignaturas(
            @PathVariable String id,
            @RequestBody List<String> idsAsignaturas) {
        log.info("Intentando agregar a estudiante con id: " + id + " asignaturas con id: " + idsAsignaturas);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.addAsignaturas(id, idsAsignaturas));
        } catch (EstudianteNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        } catch (UnprocesableException u) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", u);
        }
    }
}
