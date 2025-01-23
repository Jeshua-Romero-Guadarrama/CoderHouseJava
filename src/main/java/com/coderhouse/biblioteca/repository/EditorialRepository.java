package com.coderhouse.biblioteca.repository;

import com.coderhouse.biblioteca.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Editorial.
 */
@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {
}
