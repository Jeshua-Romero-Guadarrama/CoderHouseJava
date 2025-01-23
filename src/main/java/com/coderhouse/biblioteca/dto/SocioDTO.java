package com.coderhouse.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para la entidad Socio.
 * <p>
 * Este Data Transfer Object se utiliza para transferir la información
 * relativa a un socio en el sistema de la biblioteca, incluyendo su identificador único
 * y su nombre.
 * </p>
 */
@Schema(description = "Data Transfer Object para la entidad Socio, que contiene el identificador único y el nombre del socio.")
public class SocioDTO {

    @Schema(description = "Identificador único del socio.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Nombre del socio.", example = "Juan Pérez", required = true)
    private String nombre;

    /**
     * Constructor vacío requerido para la serialización/deserialización.
     */
    public SocioDTO() {
    }

    /**
     * Constructor que inicializa el DTO con sus atributos.
     *
     * @param id     Identificador único del socio.
     * @param nombre Nombre del socio.
     */
    public SocioDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único del socio.
     *
     * @return el ID del socio.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del socio.
     *
     * @return el nombre del socio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el identificador único del socio.
     *
     * @param id el ID del socio a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Asigna el nombre del socio.
     *
     * @param nombre el nombre del socio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
