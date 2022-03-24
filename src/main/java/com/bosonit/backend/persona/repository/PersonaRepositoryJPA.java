package com.bosonit.backend.persona.repository;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepositoryJPA extends JpaRepository<Persona, Integer> {
    @Query("SELECT p FROM Persona p WHERE p.usuario = ?1")
    Optional<Persona> findByUsername(String username);

    @Query("SELECT e FROM Persona p, Estudiante  e WHERE e.id_persona = ?1")
    Optional<Estudiante> getEstudiante(Persona persona);

    @Query("SELECT e FROM Persona p, Profesor  e WHERE e.id_persona = ?1")
    Optional<Profesor> getProfesor(Persona persona);
}
