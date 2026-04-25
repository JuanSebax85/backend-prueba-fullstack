package com.prueba.backend.repository;

import com.prueba.backend.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}