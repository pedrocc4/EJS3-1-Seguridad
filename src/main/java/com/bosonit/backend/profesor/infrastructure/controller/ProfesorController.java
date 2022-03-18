package com.bosonit.backend.profesor.infrastructure.controller;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorOutputDTO;
import com.bosonit.backend.profesor.service.ProfesorService;
import com.bosonit.backend.utils.exceptions.ProfesorNoEncontrado;
import com.bosonit.backend.utils.exceptions.UnprocesableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
public class ProfesorController {
    @Autowired
    private ProfesorService service;

    @GetMapping("profesor/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ProfesorOutputDTO getProfesor(@PathVariable String id) {
        try {
            return service.getProfesor(id);
        } catch (ProfesorNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
    }

    @GetMapping("profesores")
    @ResponseStatus(HttpStatus.OK)
    private List<ProfesorOutputDTO> getProfesores() {
        return service.getProfesores();
    }

    @PostMapping("profesor")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<ProfesorOutputDTO> addProfesor(@RequestBody ProfesorInputDTO profesorInputDTO) {
        try {
            log.info("Intentando agregar: " + profesorInputDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addProfesor(profesorInputDTO));
        } catch (UnprocesableException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", e);
        }
    }

    @PostMapping("profesor/{id}/estudiantes")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ProfesorOutputDTO> addEstudiantes(
            @PathVariable String id,
            @RequestBody List<String> ids_estudiantes) {
        try {
            log.info("Intentando agregar estudiantes: " + ids_estudiantes);
            return ResponseEntity.status(HttpStatus.OK).body(service.addEstudiantes(id, ids_estudiantes));
        } catch (ProfesorNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
    }

    @PutMapping("profesor/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ProfesorOutputDTO> actProfesor(
            @PathVariable String id,
            @RequestBody ProfesorInputDTO
                    profesorInputDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.actProfesor(id, profesorInputDTO));
        } catch (ProfesorNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        } catch (UnprocesableException u) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "error", u);
        }
    }

    @DeleteMapping("profesor/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<Void> delProfesor(@PathVariable String id) {
        try {
            service.delProfesor(id);
            return ResponseEntity.ok().build();
        } catch (ProfesorNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
    }
}
