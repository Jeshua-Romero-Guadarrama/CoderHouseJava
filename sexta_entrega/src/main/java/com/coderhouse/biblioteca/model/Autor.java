package com.coderhouse.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Autor en la Biblioteca. Un Autor puede escribir uno
 * varios libros (relación ManyToMany).
 */
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del autor
    @Column(nullable = false, length = 100)
    private String nombre;

    // Nacionalidad del autor
    @Column(length = 50)
    private String nacionalidad;

    /**
     * Relación ManyToMany con la clase Libro. En este caso, "mappedBy" indica
     * que la propiedad "autores" en la clase Libro (es la dueña de la
     * relación).
     */
    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private List<Libro> libros = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Autor() {
    }

    // Constructor adicional para conveniencia
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
