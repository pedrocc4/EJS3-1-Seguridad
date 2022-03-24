package com.bosonit.backend.estudiante.repository;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepositoryJPA extends JpaRepository<Estudiante, String> {
    @Query("SELECT p FROM Estudiante e, Profesor p WHERE  ?1 MEMBER OF p.estudiantes")
    Optional<Profesor> getProfesor(Estudiante estudiante);
}
