package com.coderhouse.biblioteca.dto;

import java.util.List;

/**
 * DTO para la entidad Libro.
 */
public class LibroDTO {

    private Long id;
    private String titulo;
    private Long editorialId;
    private List<Long> autoresIds;
    private int stock;

    public LibroDTO() {
    }

    public LibroDTO(Long id, String titulo, Long editorialId, List<Long> autoresIds) {
        this.id = id;
        this.titulo = titulo;
        this.editorialId = editorialId;
        this.autoresIds = autoresIds;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getEditorialId() {
        return editorialId;
    }

    public List<Long> getAutoresIds() {
        return autoresIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditorialId(Long editorialId) {
        this.editorialId = editorialId;
    }

    public void setAutoresIds(List<Long> autoresIds) {
        this.autoresIds = autoresIds;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
