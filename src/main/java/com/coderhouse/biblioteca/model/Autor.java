package com.coderhouse.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Autor en la Biblioteca.
 * <p>
 * Un autor puede escribir uno o varios libros. Esta clase mapea la entidad "autor"
 * en la base de datos y define una relación ManyToMany con la entidad Libro.
 * </p>
 */
@Entity
@Table(name = "autores")
@Schema(description = "Entidad que representa un autor en la biblioteca, incluyendo su nombre, nacionalidad y libros asociados.")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del autor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // Nombre del autor
    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre del autor", example = "Gabriel García Márquez", required = true)
    private String nombre;

    // Nacionalidad del autor
    @Column(length = 50)
    @Schema(description = "Nacionalidad del autor", example = "Colombiano")
    private String nacionalidad;

    /**
     * Relación ManyToMany con la clase Libro. En este caso, "mappedBy" indica que
     * la propiedad "autores" en la clase Libro es la dueña de la relación.
     */
    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    @Schema(description = "Lista de libros escritos por el autor", hidden = true)
    private List<Libro> libros = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Autor() {
    }

    /**
     * Constructor que inicializa el autor con su nombre y nacionalidad.
     *
     * @param nombre       Nombre del autor.
     * @param nacionalidad Nacionalidad del autor.
     */
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el identificador único del autor.
     *
     * @return el identificador único.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del autor.
     *
     * @return el nombre del autor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del autor.
     *
     * @param nombre el nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nacionalidad del autor.
     *
     * @return la nacionalidad.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Asigna la nacionalidad del autor.
     *
     * @param nacionalidad la nacionalidad a asignar.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene la lista de libros asociados al autor.
     *
     * @return la lista de libros.
     */
    public List<Libro> getLibros() {
        return libros;
    }

    /**
     * Asigna la lista de libros al autor.
     *
     * @param libros la lista de libros a asignar.
     */
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
