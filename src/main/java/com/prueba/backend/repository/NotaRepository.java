package com.prueba.backend.repository;

import com.prueba.backend.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}