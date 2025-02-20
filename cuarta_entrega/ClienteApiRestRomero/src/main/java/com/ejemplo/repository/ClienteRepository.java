package com.ejemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Cliente;

/**
 * Repositorio que maneja las operaciones CRUD en la tabla "cliente".
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Por defecto se heredan métodos como findById, save, delete, etc.
}
