package com.bosonit.backend.profesor.repository;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import com.bosonit.backend.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepositoryJPA extends JpaRepository<Profesor, String> {

    @Query("SELECT e FROM Profesor p, Estudiante e WHERE p.id_profesor = ?1")
    List<Estudiante> getEstudiantes(String id);
}
