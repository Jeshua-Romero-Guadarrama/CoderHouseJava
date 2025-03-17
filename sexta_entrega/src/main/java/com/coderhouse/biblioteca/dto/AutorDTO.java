package com.coderhouse.biblioteca.dto;

/**
 * DTO para la entidad Autor.
 */
public class AutorDTO {

    private Long id;
    private String nombre;
    private String nacionalidad;

    public AutorDTO() {
    }

    public AutorDTO(Long id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
