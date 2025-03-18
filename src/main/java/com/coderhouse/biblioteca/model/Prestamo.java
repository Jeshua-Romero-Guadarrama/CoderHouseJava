package com.coderhouse.biblioteca.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un préstamo de un libro por parte de un socio.
 * <p>
 * Esta entidad almacena la fecha en la que se realizó el préstamo y la fecha de devolución,
 * que puede ser nula si el libro aún no se ha devuelto.
 * </p>
 */
@Entity
@Table(name = "prestamos")
@Schema(description = "Entidad que representa un préstamo de un libro, con información del socio, el libro, y las fechas de préstamo y devolución.")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del préstamo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    /**
     * Relación ManyToOne con la entidad Socio.
     * Un socio puede tener muchos préstamos, pero un préstamo corresponde a un único socio.
     */
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Socio que realiza el préstamo", required = true)
    private Socio socio;

    /**
     * Relación ManyToOne con la entidad Libro.
     * Un libro puede ser prestado en múltiples ocasiones a lo largo del tiempo.
     */
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    @Schema(description = "Libro que se presta", required = true)
    private Libro libro;

    // Fecha en que se realiza el préstamo.
    @Column(name = "fecha_prestamo", nullable = false)
    @Schema(description = "Fecha en que se realiza el préstamo", example = "2025-03-01", required = true)
    private LocalDate fechaPrestamo;

    // Fecha en la que se devuelve el libro (puede ser null si aún no se ha devuelto).
    @Column(name = "fecha_devolucion")
    @Schema(description = "Fecha en que se devuelve el libro. Puede ser nula si el libro aún no se ha devuelto", example = "2025-03-15")
    private LocalDate fechaDevolucion;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Prestamo() {
    }

    /**
     * Constructor con parámetros para crear un nuevo préstamo.
     *
     * @param socio           El socio que realiza el préstamo.
     * @param libro           El libro que se presta.
     * @param fechaPrestamo   La fecha en que se realiza el préstamo.
     * @param fechaDevolucion La fecha en que se devuelve el libro (puede ser nula si aún no se ha devuelto).
     */
    public Prestamo(Socio socio, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del préstamo.
     *
     * @return El ID del préstamo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el socio que realizó el préstamo.
     *
     * @return El socio asociado al préstamo.
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * Asigna el socio al préstamo.
     *
     * @param socio El socio que se asignará al préstamo.
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * Obtiene el libro que se ha prestado.
     *
     * @return El libro prestado.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Asigna el libro al préstamo.
     *
     * @param libro El libro que se asignará al préstamo.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la fecha en que se realizó el préstamo.
     *
     * @return La fecha del préstamo.
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Asigna la fecha en que se realizó el préstamo.
     *
     * @param fechaPrestamo La fecha a asignar.
     */
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Obtiene la fecha en que se devolvió el libro.
     *
     * @return La fecha de devolución, o null si aún no se ha devuelto.
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Asigna la fecha de devolución del libro.
     *
     * @param fechaDevolucion La fecha en que se devolvió el libro.
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
