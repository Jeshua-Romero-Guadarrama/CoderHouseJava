package com.coderhouse.biblioteca.repository;

import com.coderhouse.biblioteca.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Socio.
 */
@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
}
