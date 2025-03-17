package com.coderhouse.biblioteca.dto;

import java.time.LocalDate;

/**
 * DTO para la entidad Préstamo.
 */
public class PrestamoDTO {

    private Long id;
    private Long socioId;
    private Long libroId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public PrestamoDTO() {
    }

    public PrestamoDTO(Long id, Long socioId, Long libroId, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id = id;
        this.socioId = socioId;
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public Long getSocioId() {
        return socioId;
    }

    public Long getLibroId() {
        return libroId;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSocioId(Long socioId) {
        this.socioId = socioId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
