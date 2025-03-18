package com.coderhouse.biblioteca.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
 */
@Entity
@Table(name = "comprobantes_prestamo")
public class ComprobantePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación ManyToOne con Socio. Un socio puede tener varios comprobantes.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "socio_id")
    private Socio socio;

    /**
     * Fecha del comprobante: la fecha en que se generó este "préstamo
     * múltiple".
     */
    @Column(name = "fecha_comprobante", nullable = false)
    private LocalDate fechaComprobante;

    /**
     * Cantidad total de libros prestados en este comprobante.
     */
    @Column(name = "total_libros", nullable = false)
    private int totalLibros;

    /**
     * Relación OneToMany con DetallePrestamo. Cada comprobante tiene varias
     * líneas de detalle (libros y cantidades).
     */
    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePrestamo> detalles = new ArrayList<>();

    public ComprobantePrestamo() {
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

    public LocalDate getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(LocalDate fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public int getTotalLibros() {
        return totalLibros;
    }

    public void setTotalLibros(int totalLibros) {
        this.totalLibros = totalLibros;
    }

    public List<DetallePrestamo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePrestamo> detalles) {
        this.detalles = detalles;
    }
}
