package com.coderhouse.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Libro dentro de la Biblioteca.
 * Un libro pertenece a una editorial (ManyToOne) y puede tener varios autores (ManyToMany).
 */
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título del libro
    @Column(nullable = false)
    private String titulo;

    /**
     * Relación ManyToOne con Editorial.
     * Muchos libros pueden pertenecer a una misma editorial.
     */
    @ManyToOne
    @JoinColumn(name = "editorial_id", nullable = false)
    @JsonBackReference
    private Editorial editorial;

    /**
     * Relación ManyToMany con Autor.
     * Se utiliza una tabla intermedia llamada "libros_autores" para gestionar esta relación.
     */
    @ManyToMany
    @JoinTable(
        name = "libros_autores",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    // Constructor vacío
    public Libro() {
    }

    // Constructor con título
    public Libro(String titulo) {
        this.titulo = titulo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Editorial getEditorial() {
        return editorial;
    }
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    public List<Autor> getAutores() {
        return autores;
    }
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
