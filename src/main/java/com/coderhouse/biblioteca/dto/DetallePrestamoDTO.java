package com.coderhouse.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa el detalle de un préstamo.
 * <p>
 * Este objeto se utiliza para especificar la cantidad de ejemplares de un libro que se solicita en un préstamo,
 * junto con la información del libro en sí.
 * </p>
 */
@Schema(description = "Detalle de préstamo que especifica la cantidad solicitada y el libro asociado.")
public class DetallePrestamoDTO {

    @Schema(description = "Cantidad de ejemplares del libro solicitados", example = "2", required = true)
    private int cantidad;
    
    @Schema(description = "Información del libro que se está prestando", required = true)
    private LibroDTO libro;

    /**
     * Constructor vacío requerido para la serialización y deserialización.
     */
    public DetallePrestamoDTO() {
    }

    /**
     * Obtiene la cantidad de ejemplares solicitados.
     *
     * @return la cantidad de ejemplares.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de ejemplares solicitados.
     *
     * @param cantidad la cantidad de ejemplares.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la información del libro prestado.
     *
     * @return el objeto LibroDTO que representa al libro.
     */
    public LibroDTO getLibro() {
        return libro;
    }

    /**
     * Establece la información del libro prestado.
     *
     * @param libro el objeto LibroDTO que representa al libro.
     */
    public void setLibro(LibroDTO libro) {
        this.libro = libro;
    }
}
