package com.coderhouse.biblioteca.entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la Editorial que publica un conjunto de Libros.
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
     * Una editorial puede tener muchos libros.
     * El 'mappedBy' indica que la relación se mapea desde la propiedad
     * 'editorial' de la clase Libro.
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