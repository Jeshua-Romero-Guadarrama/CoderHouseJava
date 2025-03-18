package com.coderhouse.biblioteca.dto;

/**
 * DTO para la entidad Socio.
 */
public class SocioDTO {

    private Long id;
    private String nombre;

    public SocioDTO() {
    }

    public SocioDTO(Long id, String nombre) {
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
