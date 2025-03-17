package com.coderhouse.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_prestamo")
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación ManyToOne con ComprobantePrestamo. Muchos detalles pertenecen a
     * 1 comprobante.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "comprobante_id")
    @JsonBackReference
    private ComprobantePrestamo comprobante;

    /**
     * Relación ManyToOne con Libro. Un detalle se refiere a un solo libro.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    /**
     * Cantidad de ejemplares de este libro prestados en esta línea.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    public DetallePrestamo() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public ComprobantePrestamo getComprobante() {
        return comprobante;
    }

    public void setComprobante(ComprobantePrestamo comprobante) {
        this.comprobante = comprobante;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
