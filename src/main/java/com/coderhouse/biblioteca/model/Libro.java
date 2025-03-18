package com.coderhouse.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * <p>
 * Un libro pertenece a una Editorial (relación ManyToOne) y puede estar escrito por varios Autores (relación ManyToMany).
 * Se utiliza una tabla intermedia ("libros_autores") para gestionar la relación ManyToMany.
 * </p>
 */
@Entity
@Table(name = "libros")
@Schema(description = "Entidad que representa un libro en la biblioteca, incluyendo su título, editorial, autores y stock disponible.")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del libro", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // Título del libro
    @Column(nullable = false)
    @Schema(description = "Título del libro", example = "El Quijote", required = true)
    private String titulo;

    /**
     * Relación ManyToOne con la entidad Editorial.
     * Muchos libros pueden pertenecer a una misma editorial.
     */
    @ManyToOne
    @JoinColumn(name = "editorial_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Editorial a la que pertenece el libro", required = true)
    private Editorial editorial;

    /**
     * Relación ManyToMany con la entidad Autor.
     * Se utiliza una tabla intermedia llamada "libros_autores" para gestionar esta relación.
     */
    @ManyToMany
    @JoinTable(
        name = "libros_autores",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @Schema(description = "Lista de autores que han escrito el libro")
    private List<Autor> autores = new ArrayList<>();

    // Stock de ejemplares disponibles
    @Column(name = "stock", nullable = false)
    @Schema(description = "Número de ejemplares disponibles del libro", example = "10", required = true)
    private int stock;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Libro() {
    }

    /**
     * Constructor que permite inicializar el libro con un título.
     * El stock debe ser asignado posteriormente.
     * 
     * @param titulo El título del libro.
     */
    public Libro(String titulo) {
        this.titulo = titulo;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del libro.
     * 
     * @return El ID del libro.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el título del libro.
     * 
     * @return El título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Asigna el título del libro.
     * 
     * @param titulo El título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la editorial a la que pertenece el libro.
     * 
     * @return La editorial del libro.
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * Asigna la editorial del libro.
     * 
     * @param editorial La editorial a asignar.
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene la lista de autores asociados al libro.
     * 
     * @return Una lista de objetos Autor.
     */
    public List<Autor> getAutores() {
        return autores;
    }

    /**
     * Asigna la lista de autores al libro.
     * 
     * @param autores La lista de autores.
     */
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    /**
     * Obtiene el número de ejemplares disponibles del libro.
     * 
     * @return El stock del libro.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Asigna el número de ejemplares disponibles del libro.
     * 
     * @param stock El stock a asignar.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
