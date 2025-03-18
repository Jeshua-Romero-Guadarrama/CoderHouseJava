package com.coderhouse.biblioteca.model;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa una Editorial de libros.
 * <p>
 * Una editorial es responsable de la publicación de uno o varios libros, 
 * estableciendo una relación OneToMany con la entidad Libro.
 * </p>
 */
@Entity
@Table(name = "editoriales")
@Schema(description = "Entidad que representa una editorial, responsable de la publicación de libros en la biblioteca.")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la editorial", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // Nombre de la editorial
    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre de la editorial", example = "Editorial Ejemplo", required = true)
    private String nombre;

    /**
     * Relación OneToMany con la clase Libro.
     * <p>
     * El atributo "mappedBy" indica que la entidad Libro es la propietaria de la relación,
     * manteniendo la referencia de la editorial.
     * </p>
     */
    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "Lista de libros publicados por la editorial")
    private List<Libro> libros = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Editorial() {
    }

    /**
     * Constructor que permite inicializar la editorial con su nombre.
     * 
     * @param nombre El nombre de la editorial.
     */
    public Editorial(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único de la editorial.
     * 
     * @return El ID de la editorial.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la editorial.
     * 
     * @return El nombre de la editorial.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre de la editorial.
     * 
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de libros asociados a la editorial.
     * 
     * @return Una lista de objetos Libro.
     */
    public List<Libro> getLibros() {
        return libros;
    }

    /**
     * Asigna la lista de libros asociados a la editorial.
     * 
     * @param libros La lista de libros a asignar.
     */
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
