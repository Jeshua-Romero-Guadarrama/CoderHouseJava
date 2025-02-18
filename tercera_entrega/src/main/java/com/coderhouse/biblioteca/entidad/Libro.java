package com.coderhouse.biblioteca.entidad;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Representa un Libro de la biblioteca.
 */
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    /**
     * Muchos libros pueden asociarse a una misma editorial (ManyToOne).
     */
    @ManyToOne
    @JoinColumn(name = "editorial_id", nullable = false)
    private Editorial editorial;

    /**
     * Relación ManyToMany con Autor.
     * Se utiliza una tabla intermedia llamada 'libros_autores'.
     */
    @ManyToMany
    @JoinTable(
        name = "libros_autores",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Libro() {
    }

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
