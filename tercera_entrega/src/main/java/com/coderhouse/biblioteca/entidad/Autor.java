package com.coderhouse.biblioteca.entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un Autor que puede escribir uno o varios libros.
 */
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;

    /**
     * Relación ManyToMany inversa: se referencia desde la propiedad 'autores'
     * en la clase Libro con 'mappedBy'.
     */
    @ManyToMany(mappedBy = "autores")
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
