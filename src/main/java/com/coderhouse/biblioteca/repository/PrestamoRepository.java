package com.coderhouse.biblioteca.repository;

import com.coderhouse.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Prestamo.
 */
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
