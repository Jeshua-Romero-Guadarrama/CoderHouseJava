package com.coderhouse.biblioteca.repository;

import com.coderhouse.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Autor que provee métodos CRUD y consultas personalizadas.
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
