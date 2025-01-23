package com.coderhouse.biblioteca.model;

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
 * Entidad que representa el detalle de un préstamo dentro de un comprobante.
 * <p>
 * Cada detalle indica la cantidad de ejemplares de un libro específico que se prestan en
 * el contexto de un comprobante de préstamo múltiple.
 * </p>
 */
@Entity
@Table(name = "detalles_prestamo")
@Schema(description = "Entidad que representa el detalle de un préstamo, asociada a un comprobante y a un libro, especificando la cantidad de ejemplares prestados.")
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del detalle de préstamo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    /**
     * Relación ManyToOne con ComprobantePrestamo. Muchos detalles pertenecen a un mismo comprobante.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "comprobante_id")
    @JsonBackReference
    @Schema(description = "El comprobante al que pertenece este detalle de préstamo", required = true)
    private ComprobantePrestamo comprobante;

    /**
     * Relación ManyToOne con Libro. Un detalle se refiere a un solo libro.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "libro_id")
    @Schema(description = "El libro que se está prestando en este detalle", required = true)
    private Libro libro;

    /**
     * Cantidad de ejemplares de este libro prestados en este detalle.
     */
    @Column(name = "cantidad", nullable = false)
    @Schema(description = "Cantidad de ejemplares del libro prestados en este detalle", example = "2", required = true)
    private int cantidad;

    /**
     * Constructor vacío requerido por JPA.
     */
    public DetallePrestamo() {
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del detalle.
     * @return El ID del detalle de préstamo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el comprobante asociado a este detalle.
     * @return El comprobante de préstamo.
     */
    public ComprobantePrestamo getComprobante() {
        return comprobante;
    }

    /**
     * Asigna el comprobante al cual pertenece este detalle.
     * @param comprobante El comprobante de préstamo.
     */
    public void setComprobante(ComprobantePrestamo comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Obtiene el libro relacionado con este detalle.
     * @return El libro prestado.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Asigna el libro que se presta en este detalle.
     * @param libro El libro a asociar.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la cantidad de ejemplares prestados.
     * @return La cantidad de libros prestados en este detalle.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad de ejemplares prestados.
     * @param cantidad La cantidad de libros.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
