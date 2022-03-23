package com.bosonit.backend.profesor.infrastructure.controller;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;
import com.bosonit.backend.profesor.service.ProfesorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("Intentando encontrar profesor con id: " + id);
        log.info("Tipo respuesta: " + outputType);
        if (outputType.equals("simple"))
            return ResponseEntity.status(HttpStatus.OK).body(service.getProfesor(id));
        else if (outputType.equals("full")) {
            log.info("ha ejecutado full");
            return ResponseEntity.status(HttpStatus.OK).body(service.getProfesor1(id));
        }
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

        log.info("Intentando agregar: " + profesorInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addProfesor(profesorInputDTO));

    }

    @PostMapping("profesor/{id}/estudiantes")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ProfesorOutputDTO> addEstudiantes(
            @PathVariable String id,
            @RequestBody List<String> ids_estudiantes) {

        log.info("Intentando agregar estudiantes: " + ids_estudiantes);
        return ResponseEntity.status(HttpStatus.OK).body(service.addEstudiantes(id, ids_estudiantes));

    }

    @GetMapping("profesor/{id}/estudiantes")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<List<EstudianteOutputDTO>> getEstudiantes(
            @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiantes(id));
    }

    @PutMapping("profesor/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ProfesorOutputDTO> actProfesor(
            @PathVariable String id,
            @RequestBody ProfesorInputDTO
                    profesorInputDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(service.actProfesor(id, profesorInputDTO));

    }

    @DeleteMapping("profesor/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<Void> delProfesor(@PathVariable String id) {

        service.delProfesor(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("profesor/{id}/persona") // FIXME post, put, patch?
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ProfesorPersonaOutputDTO> addPersona(
            @PathVariable(name = "id") String id_profesor,
            @RequestParam(name = "persona") int id_persona) {
//
        return ResponseEntity.status(HttpStatus.OK).body(service.addPersona(id_profesor, id_persona));

    }
}
