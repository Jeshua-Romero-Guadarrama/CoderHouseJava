package com.coderhouse.biblioteca.dto;

public class DetallePrestamoDTO {

    private int cantidad;
    private LibroDTO libro;

    public DetallePrestamoDTO() {
    }

    // Getters y setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LibroDTO getLibro() {
        return libro;
    }

    public void setLibro(LibroDTO libro) {
        this.libro = libro;
    }
}
