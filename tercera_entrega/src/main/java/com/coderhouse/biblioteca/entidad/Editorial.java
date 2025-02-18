package com.coderhouse.biblioteca.entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Editorial que publica varios Libros.
 */
@Entity
@Table(name = "editoriales")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Una editorial puede poseer muchos libros.
     * 'mappedBy' indica que esta relación se gestiona desde
     * la propiedad 'editorial' en la clase Libro.
     */
    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Editorial() {
    }

    public Editorial(String nombre) {
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
    public List<Libro> getLibros() {
        return libros;
    }
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
