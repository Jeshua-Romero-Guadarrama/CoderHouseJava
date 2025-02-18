package com.coderhouse.biblioteca.entidad;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Representa un Préstamo de un Libro a un Socio,
 * con fechas de préstamo y de devolución.
 */
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Muchos préstamos pertenecen a un mismo socio (ManyToOne).
     */
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    /**
     * Muchos préstamos pueden apuntar al mismo libro.
     */
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    // Constructor vacío requerido por JPA
    public Prestamo() {
    }

    // Constructor adicional
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
