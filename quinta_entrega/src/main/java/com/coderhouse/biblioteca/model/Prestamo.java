package com.coderhouse.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que representa un Préstamo de un libro por parte de un socio,
 * Indicando fechas de préstamo y de devolución.
 */
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación ManyToOne con Socio.
     * Un socio puede tener muchos préstamos, pero un préstamo corresponde a un solo socio.
     */
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    /**
     * Relación ManyToOne con Libro.
     * Un libro puede tener muchos préstamos a lo largo del tiempo.
     */
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    // Fecha en que se realiza el préstamo
    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    // Fecha en la que se devuelve el libro (puede ser null si aún no se ha devuelto)
    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    // Constructor vacío
    public Prestamo() {
    }

    // Constructor con parámetros
    public Prestamo(Socio socio, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public Socio getSocio() {
        return socio;
    }
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
