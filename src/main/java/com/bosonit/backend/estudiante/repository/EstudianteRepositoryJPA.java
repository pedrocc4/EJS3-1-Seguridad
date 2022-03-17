package com.bosonit.backend.estudiante.repository;

import com.bosonit.backend.estudiante.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepositoryJPA extends JpaRepository<Estudiante, String> {

}
