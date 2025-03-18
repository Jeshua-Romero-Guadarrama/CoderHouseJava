package com.coderhouse.biblioteca.repository;

import com.coderhouse.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Libro.
 */
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
