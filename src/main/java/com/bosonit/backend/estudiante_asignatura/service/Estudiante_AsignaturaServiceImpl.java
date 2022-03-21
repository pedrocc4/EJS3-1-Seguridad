package com.bosonit.backend.estudiante_asignatura.service;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.mapper.AsignaturaMapper;
import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante_asignatura.domain.Estudiante_Asignatura;
import com.bosonit.backend.estudiante_asignatura.repository.Estudiante_AsignaturaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class Estudiante_AsignaturaServiceImpl implements Estudiante_AsignaturaService {

    @Autowired
    private Estudiante_AsignaturaRepositoryJPA repository;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    public List<AsignaturaOutputDTO> getAsignaturas(String id) {
        return asignaturaMapper.toDTOList(repository.getAsignaturas(id));
    }

    @Override
    public void addEstudiante_Asignatura(Estudiante estudiante, Asignatura asignatura) {
        //FIXME recibira mas atributos (fecha...)
        if (repository.findEstudiante_asignatura(estudiante, asignatura).isEmpty()) {
            Estudiante_Asignatura estudiante_asignatura = new Estudiante_Asignatura();
            estudiante_asignatura.setId_estudiante(estudiante);
            estudiante_asignatura.setId_asignatura(asignatura);
            estudiante_asignatura.setInitial_date(Date.from(Instant.now()));
            repository.save(estudiante_asignatura);
        }

    }
}
