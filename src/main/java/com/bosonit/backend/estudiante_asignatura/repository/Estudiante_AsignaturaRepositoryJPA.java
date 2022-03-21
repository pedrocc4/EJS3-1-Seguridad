package com.bosonit.backend.estudiante_asignatura.repository;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante_asignatura.domain.Estudiante_Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Estudiante_AsignaturaRepositoryJPA extends JpaRepository<Estudiante_Asignatura, String> {
    @Query("SELECT a FROM Estudiante e, Asignatura a WHERE e.id_estudiante=?1 ")
    List<Asignatura> getAsignaturas(String id);

    @Query("SELECT ea FROM Estudiante_Asignatura ea WHERE ea.id_estudiante = ?1 AND ea.id_asignatura = ?2")
    Optional<Estudiante_Asignatura> findEstudiante_asignatura(Estudiante estudiante, Asignatura asignatura);
}
