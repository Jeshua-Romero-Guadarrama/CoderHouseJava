package com.coderhouse.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.biblioteca.model.ComprobantePrestamo;

@Repository
public interface ComprobantePrestamoRepository 
       extends JpaRepository<ComprobantePrestamo, Long> {
}
