package com.bosonit.backend.profesor.infrastructure.controller;

import com.bosonit.backend.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;
import com.bosonit.backend.profesor.service.ProfesorService;
import com.bosonit.backend.utils.exceptions.PersonaNoEncontrada;
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
    private ResponseEntity<Object> getProfesor(
            @PathVariable String id,
            @RequestParam(defaultValue = "simple") String outputType) {
        try {
            log.info("Intentando encontrar profesor con id: " + id);
            log.info("Tipo respuesta: " + outputType);
            if (outputType.equals("simple"))
                return ResponseEntity.status(HttpStatus.OK).body(service.getProfesor(id));
            else if (outputType.equals("full")) {
                log.info("ha ejecutado full");
                return ResponseEntity.status(HttpStatus.OK).body(service.getProfesor1(id));
            }
        } catch (ProfesorNoEncontrado e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        } // no es necesario, pero para que no salte error
        return ResponseEntity.status(HttpStatus.OK).body(service.getProfesor(id));
    }

    @GetMapping("profesores")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<Object> getProfesores(
            @RequestParam(defaultValue = "simple") String outputType) {
        log.info("Buscando a todos los profesores, tipo: " + outputType);
        if (outputType.equals("simple"))
            return ResponseEntity.status(HttpStatus.OK).body(service.getProfesores());
        else if (outputType.equals("full"))
            return ResponseEntity.status(HttpStatus.OK).body(service.getProfesores1());

        return ResponseEntity.status(HttpStatus.OK).body(service.getProfesores());
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

    @GetMapping

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

    @PutMapping("profesor/{id}/persona") // FIXME post, put, patch?
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ProfesorPersonaOutputDTO> addPersona(
            @PathVariable(name = "id") String id_profesor,
            @RequestParam(name = "persona") int id_persona) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.addPersona(id_profesor, id_persona));
        } catch (ProfesorNoEncontrado | PersonaNoEncontrada e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error", e);
        }
    }
}
