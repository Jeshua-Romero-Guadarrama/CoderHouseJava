package com.coderhouse.biblioteca.repository;

import com.coderhouse.biblioteca.model.DetallePrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePrestamoRepository
        extends JpaRepository<DetallePrestamo, Long> {
}
