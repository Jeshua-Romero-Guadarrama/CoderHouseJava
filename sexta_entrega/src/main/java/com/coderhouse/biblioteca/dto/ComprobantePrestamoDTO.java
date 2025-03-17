package com.coderhouse.biblioteca.dto;

import java.util.List;

/**
 * DTO para recibir la solicitud de creación de un "comprobante de préstamo".
 */
public class ComprobantePrestamoDTO {

    private SocioDTO socio;
    private List<DetallePrestamoDTO> lineas;

    public ComprobantePrestamoDTO() {
    }

    // Getters y Setters
    public SocioDTO getSocio() {
        return socio;
    }

    public void setSocio(SocioDTO socio) {
        this.socio = socio;
    }

    public List<DetallePrestamoDTO> getLineas() {
        return lineas;
    }

    public void setLineas(List<DetallePrestamoDTO> lineas) {
        this.lineas = lineas;
    }
}
