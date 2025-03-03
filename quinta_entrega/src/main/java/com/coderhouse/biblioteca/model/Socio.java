package com.coderhouse.biblioteca.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un Socio de la biblioteca.
 * Un socio puede tener múltiples préstamos.
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
     * Relación OneToMany con Prestamo:
     * Un socio puede tener muchos préstamos (en diferentes momentos).
     * orphanRemoval = true elimina préstamos si se elimina el socio.
     */
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
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
