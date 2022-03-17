package com.bosonit.backend.profesor.infrastructure.controller;

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

}
