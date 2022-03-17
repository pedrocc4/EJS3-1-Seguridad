package com.bosonit.backend.profesor.repository;

import com.bosonit.backend.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepositoryJPA extends JpaRepository<Profesor, String> {
}
