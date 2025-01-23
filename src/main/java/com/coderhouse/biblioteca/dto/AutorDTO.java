package com.coderhouse.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para la entidad Autor.
 * <p>
 * Este objeto se utiliza para transferir la información del autor entre capas de la aplicación.
 * </p>
 */
@Schema(description = "DTO para la entidad Autor, utilizado para transferir información de los autores.")
public class AutorDTO {

    @Schema(description = "Identificador único del autor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del autor", example = "Gabriel García Márquez", required = true)
    private String nombre;

    @Schema(description = "Nacionalidad del autor", example = "Colombiano")
    private String nacionalidad;

    /**
     * Constructor vacío requerido para la serialización/deserialización.
     */
    public AutorDTO() {
    }

    /**
     * Constructor con parámetros para inicializar todas las propiedades.
     *
     * @param id            Identificador único del autor.
     * @param nombre        Nombre del autor.
     * @param nacionalidad  Nacionalidad del autor.
     */
    public AutorDTO(Long id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Getters y setters

    /**
     * Obtiene el identificador único del autor.
     *
     * @return el id del autor.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del autor.
     *
     * @param id el id que se asignará.
     */
    public void setId(Long id) {
        this.id = id;
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
     * Establece el nombre del autor.
     *
     * @param nombre el nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nacionalidad del autor.
     *
     * @return la nacionalidad del autor.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del autor.
     *
     * @param nacionalidad la nacionalidad a asignar.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
