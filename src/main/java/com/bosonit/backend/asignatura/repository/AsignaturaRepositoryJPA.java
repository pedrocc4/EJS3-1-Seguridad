package com.bosonit.backend.asignatura.repository;

import com.bosonit.backend.asignatura.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepositoryJPA extends JpaRepository<Asignatura, String> {
}
