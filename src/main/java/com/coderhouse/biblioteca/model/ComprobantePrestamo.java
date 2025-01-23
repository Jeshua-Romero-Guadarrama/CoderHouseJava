package com.coderhouse.biblioteca.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Representa un "comprobante" de múltiples préstamos en una sola operación.
 * <p>
 * Esta entidad agrupa la información referente a un conjunto de préstamos realizados por un socio, 
 * registrando la fecha del comprobante, el total de libros prestados y la lista de detalles de cada préstamo.
 * </p>
 */
@Entity
@Table(name = "comprobantes_prestamo")
@Schema(description = "Entidad que representa un comprobante de préstamo múltiple, conteniendo información del socio, la fecha del comprobante, el total de libros y los detalles del préstamo.")
public class ComprobantePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del comprobante", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    /**
     * Relación ManyToOne con Socio. Un socio puede tener varios comprobantes.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "socio_id")
    @Schema(description = "Socio que realiza el préstamo", required = true)
    private Socio socio;

    /**
     * Fecha del comprobante: la fecha en que se generó este "préstamo múltiple".
     */
    @Column(name = "fecha_comprobante", nullable = false)
    @Schema(description = "Fecha en que se generó el comprobante", example = "2025-03-17", required = true)
    private LocalDate fechaComprobante;

    /**
     * Cantidad total de libros prestados en este comprobante.
     */
    @Column(name = "total_libros", nullable = false)
    @Schema(description = "Cantidad total de libros prestados en este comprobante", example = "5", required = true)
    private int totalLibros;

    /**
     * Relación OneToMany con DetallePrestamo. Cada comprobante tiene varias líneas de detalle (libros y cantidades).
     */
    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "Lista de detalles de préstamo asociados al comprobante", hidden = true)
    private List<DetallePrestamo> detalles = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public ComprobantePrestamo() {
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del comprobante.
     *
     * @return el ID del comprobante.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el socio asociado al comprobante.
     *
     * @return el socio que realizó el préstamo.
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * Asigna el socio al comprobante.
     *
     * @param socio el socio a asignar.
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * Obtiene la fecha en que se generó el comprobante.
     *
     * @return la fecha del comprobante.
     */
    public LocalDate getFechaComprobante() {
        return fechaComprobante;
    }

    /**
     * Asigna la fecha en que se generó el comprobante.
     *
     * @param fechaComprobante la fecha a asignar.
     */
    public void setFechaComprobante(LocalDate fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    /**
     * Obtiene el total de libros prestados registrados en el comprobante.
     *
     * @return el total de libros.
     */
    public int getTotalLibros() {
        return totalLibros;
    }

    /**
     * Asigna el total de libros prestados en el comprobante.
     *
     * @param totalLibros el total de libros a asignar.
     */
    public void setTotalLibros(int totalLibros) {
        this.totalLibros = totalLibros;
    }

    /**
     * Obtiene la lista de detalles de préstamo asociados al comprobante.
     *
     * @return la lista de detalles.
     */
    public List<DetallePrestamo> getDetalles() {
        return detalles;
    }

    /**
     * Asigna la lista de detalles de préstamo al comprobante.
     *
     * @param detalles la lista de detalles a asignar.
     */
    public void setDetalles(List<DetallePrestamo> detalles) {
        this.detalles = detalles;
    }
}
