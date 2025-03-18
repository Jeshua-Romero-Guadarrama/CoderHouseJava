package com.coderhouse.biblioteca.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para la entidad Préstamo.
 * <p>
 * Este objeto se utiliza para transferir los datos relacionados con un préstamo de un libro
 * realizado por un socio. Incluye el identificador del préstamo, el ID del socio, el ID del libro,
 * la fecha en que se realizó el préstamo y la fecha en que se devolvió el libro (si corresponde).
 * </p>
 */
@Schema(description = "Data Transfer Object para la entidad Préstamo, que incluye el ID del préstamo, el ID del socio, el ID del libro, la fecha del préstamo y la fecha de devolución.")
public class PrestamoDTO {

    @Schema(description = "Identificador único del préstamo.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Identificador del socio que realiza el préstamo.", example = "1", required = true)
    private Long socioId;
    
    @Schema(description = "Identificador del libro que se presta.", example = "10", required = true)
    private Long libroId;
    
    @Schema(description = "Fecha en la que se realizó el préstamo.", example = "2025-03-17", required = true)
    private LocalDate fechaPrestamo;
    
    @Schema(description = "Fecha en la que se devolvió el libro. Puede ser null si aún no se ha devuelto.", example = "2025-04-01", required = false)
    private LocalDate fechaDevolucion;

    /**
     * Constructor vacío requerido para la serialización/deserialización.
     */
    public PrestamoDTO() {
    }

    /**
     * Constructor que inicializa el DTO con sus atributos.
     *
     * @param id              Identificador único del préstamo.
     * @param socioId         Identificador del socio que realiza el préstamo.
     * @param libroId         Identificador del libro prestado.
     * @param fechaPrestamo   Fecha en la que se realizó el préstamo.
     * @param fechaDevolucion Fecha en la que se devolvió el libro (puede ser null si aún no se ha devuelto).
     */
    public PrestamoDTO(Long id, Long socioId, Long libroId, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id = id;
        this.socioId = socioId;
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Obtiene el identificador único del préstamo.
     *
     * @return el ID del préstamo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el identificador del socio que realiza el préstamo.
     *
     * @return el ID del socio.
     */
    public Long getSocioId() {
        return socioId;
    }

    /**
     * Obtiene el identificador del libro prestado.
     *
     * @return el ID del libro.
     */
    public Long getLibroId() {
        return libroId;
    }

    /**
     * Obtiene la fecha en la que se realizó el préstamo.
     *
     * @return la fecha del préstamo.
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Obtiene la fecha en la que se devolvió el libro.
     *
     * @return la fecha de devolución, o null si aún no se ha devuelto.
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Asigna el identificador único del préstamo.
     *
     * @param id el ID del préstamo a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Asigna el identificador del socio que realiza el préstamo.
     *
     * @param socioId el ID del socio.
     */
    public void setSocioId(Long socioId) {
        this.socioId = socioId;
    }

    /**
     * Asigna el identificador del libro prestado.
     *
     * @param libroId el ID del libro.
     */
    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    /**
     * Asigna la fecha en la que se realizó el préstamo.
     *
     * @param fechaPrestamo la fecha del préstamo.
     */
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Asigna la fecha en la que se devolvió el libro.
     *
     * @param fechaDevolucion la fecha de devolución, o null si aún no se ha devuelto.
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
