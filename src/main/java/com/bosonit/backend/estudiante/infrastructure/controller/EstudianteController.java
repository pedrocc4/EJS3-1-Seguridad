package com.bosonit.backend.estudiante.infrastructure.controller;

import com.bosonit.backend.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import com.bosonit.backend.estudiante.service.EstudianteService;
import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addEstudiante(estudianteInputDTO));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("estudiantes")
    public ResponseEntity<Object> getEstudiantes(
            @RequestParam(defaultValue = "simple") String outputType) {
        log.info("Buscando a todos los estudiantes, tipo: " + outputType);
        if (outputType.equals("simple")) return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiantes());
        else if (outputType.equals("full")) return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiantes1());
        return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiantes());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("estudiante/{id}")
    public ResponseEntity<Object> getEstudiante(
            @PathVariable String id,
            @RequestParam(defaultValue = "simple") String outputType) {
        log.info("Intentando buscar estudiante con id: " + id);
        log.info("Valor outputType: " + outputType);
        if (outputType.equals("simple"))
            return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiante(id));
        else if (outputType.equals("full"))
            return ResponseEntity.status(HttpStatus.OK).body(service.getEstudiante2(id));

        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("estudiante/{id}")
    //@PatchMapping -- para un campo en concreto
    public ResponseEntity<EstudianteOutputDTO> actEstudiante(
            @PathVariable String id,
            @RequestBody EstudianteInputDTO estudianteInputDTO) throws Exception {
        log.info("Intentando actualizar estudiante con id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.actEstudiante(id, estudianteInputDTO));

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("estudiante/{id}")
    public ResponseEntity<Void> delEstudiante(
            @PathVariable String id) {
        log.info("Intentando borrar estudiante con id: " + id);
        service.delEstudiante(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("estudiante/{id}/asignaturas")
    public ResponseEntity<EstudianteOutputDTO> addAsignaturas(
            @PathVariable String id,
            @RequestBody List<String> idsAsignaturas) {
        log.info("Intentando agregar a estudiante con id: " + id + " asignaturas con id: " + idsAsignaturas);
        return ResponseEntity.status(HttpStatus.OK).body(service.addAsignaturas(id, idsAsignaturas));

    }

    // Podria llegar a omitirse puesto que la persona se asigna en el momento de creacion del cliente (en un principio)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("estudiante/{id}/persona")
    public ResponseEntity<EstudiantePersonaOutputDTO> addPersona(
            @PathVariable(name = "id") String id_estudiante,
            @RequestParam(name = "persona") int id_persona) {
        log.info("Intentando agregar persona con id: " + id_persona + " a estudiante con id: " + id_estudiante);
        return ResponseEntity.status(HttpStatus.OK).body(service.addPersona(id_estudiante, id_persona));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("estudiante/{id}/profesor")
    public ResponseEntity<ProfesorOutputDTO> getProfesor(
            @PathVariable String id) {
        log.info("Intentando obtener profesor de estudiante con id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.getProfesor(id));
    }

}
