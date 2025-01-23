package com.coderhouse.biblioteca.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para recibir la solicitud de creación de un "comprobante de préstamo".
 * <p>
 * Este objeto se utiliza para transferir la información necesaria para generar un comprobante
 * de préstamo múltiple, el cual agrupa los detalles de los libros solicitados por un socio.
 * </p>
 */
@Schema(description = "DTO para la creación de un comprobante de préstamo múltiple.")
public class ComprobantePrestamoDTO {

    @Schema(description = "Datos del socio que solicita el préstamo", required = true)
    private SocioDTO socio;
    
    @Schema(description = "Lista de detalles del préstamo, que incluyen libros y cantidades solicitadas", required = true)
    private List<DetallePrestamoDTO> lineas;

    /**
     * Constructor vacío requerido para la serialización y deserialización.
     */
    public ComprobantePrestamoDTO() {
    }

    /**
     * Obtiene el socio asociado al comprobante de préstamo.
     *
     * @return el objeto SocioDTO que contiene la información del socio.
     */
    public SocioDTO getSocio() {
        return socio;
    }

    /**
     * Establece el socio asociado al comprobante de préstamo.
     *
     * @param socio el objeto SocioDTO que representa al socio.
     */
    public void setSocio(SocioDTO socio) {
        this.socio = socio;
    }

    /**
     * Obtiene la lista de detalles del préstamo.
     *
     * @return la lista de objetos DetallePrestamoDTO que representan cada línea de detalle del préstamo.
     */
    public List<DetallePrestamoDTO> getLineas() {
        return lineas;
    }

    /**
     * Establece la lista de detalles del préstamo.
     *
     * @param lineas la lista de DetallePrestamoDTO con los detalles del préstamo.
     */
    public void setLineas(List<DetallePrestamoDTO> lineas) {
        this.lineas = lineas;
    }
}
