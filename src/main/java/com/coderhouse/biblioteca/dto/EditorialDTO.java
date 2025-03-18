package com.coderhouse.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para la entidad Editorial.
 * <p>
 * Este objeto se utiliza para transferir la información básica de una editorial
 * a través de la API, facilitando el intercambio de datos entre capas.
 * </p>
 */
@Schema(description = "Data Transfer Object para la entidad Editorial, que contiene el ID y el nombre de la editorial.")
public class EditorialDTO {

    @Schema(description = "Identificador único de la editorial.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Nombre de la editorial.", example = "Editorial ABC", required = true)
    private String nombre;

    /**
     * Constructor vacío requerido para la serialización y deserialización.
     */
    public EditorialDTO() {
    }

    /**
     * Constructor que inicializa el DTO con su ID y nombre.
     *
     * @param id     el identificador único de la editorial.
     * @param nombre el nombre de la editorial.
     */
    public EditorialDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único de la editorial.
     *
     * @return el identificador de la editorial.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la editorial.
     *
     * @param id el identificador de la editorial.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la editorial.
     *
     * @return el nombre de la editorial.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la editorial.
     *
     * @param nombre el nombre de la editorial.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
