package com.coderhouse.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Socio de la biblioteca. Un socio puede tener
 * múltiples préstamos.
 */
@Entity
@Table(name = "socios")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del socio
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Relación OneToMany con Prestamo: Un socio puede tener muchos préstamos
     * (en diferentes momentos). orphanRemoval = true elimina préstamos si se
     * elimina el socio.
     */
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor vacío
    public Socio() {
    }

    // Constructor con nombre
    public Socio(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
