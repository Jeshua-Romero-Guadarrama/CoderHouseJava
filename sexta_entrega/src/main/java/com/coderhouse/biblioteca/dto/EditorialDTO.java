package com.coderhouse.biblioteca.dto;

/**
 * DTO para la entidad Editorial.
 */
public class EditorialDTO {

    private Long id;
    private String nombre;

    public EditorialDTO() {
    }

    public EditorialDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
