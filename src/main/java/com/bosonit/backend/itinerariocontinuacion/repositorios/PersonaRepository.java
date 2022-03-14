package com.bosonit.backend.itinerariocontinuacion.repositorios;

import com.bosonit.backend.itinerariocontinuacion.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query("SELECT p FROM Persona p WHERE p.usuario = ?1")
    Optional<Persona> findAllUsername(String username);
}
