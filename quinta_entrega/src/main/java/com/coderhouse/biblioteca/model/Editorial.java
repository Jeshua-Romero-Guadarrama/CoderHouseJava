package com.coderhouse.biblioteca.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una Editorial de libros.
 * Una editorial puede publicar muchos libros (relación OneToMany).
 */
@Entity
@Table(name = "editoriales")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la editorial
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Relación OneToMany con la clase Libro.
     * "mappedBy" indica que la propiedad "editorial" en la clase Libro (es la dueña de la relación).
     */
    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    // Constructor vacío
    public Editorial() {
    }

    // Constructor para conveniencia
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
