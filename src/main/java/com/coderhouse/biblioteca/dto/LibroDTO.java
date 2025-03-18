package com.coderhouse.biblioteca.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para la entidad Libro.
 * <p>
 * Este objeto se utiliza para transferir datos de la entidad Libro entre las diferentes capas de la aplicación,
 * permitiendo el manejo de la información esencial del libro, como su título, la editorial asociada, los autores y el stock.
 * </p>
 */
@Schema(description = "Data Transfer Object para la entidad Libro, que contiene el ID, título, ID de la editorial, lista de IDs de los autores y el stock disponible.")
public class LibroDTO {

    @Schema(description = "Identificador único del libro.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Título del libro.", example = "El Quijote", required = true)
    private String titulo;
    
    @Schema(description = "Identificador de la editorial a la que pertenece el libro.", example = "2", required = true)
    private Long editorialId;
    
    @Schema(description = "Lista de identificadores de los autores asociados al libro.", example = "[1, 3, 5]", required = true)
    private List<Long> autoresIds;
    
    @Schema(description = "Cantidad de ejemplares disponibles del libro.", example = "10", required = true)
    private int stock;

    /**
     * Constructor vacío requerido para la serialización/deserialización.
     */
    public LibroDTO() {
    }

    /**
     * Constructor que inicializa el DTO con sus atributos principales.
     *
     * @param id           el identificador único del libro.
     * @param titulo       el título del libro.
     * @param editorialId  el ID de la editorial asociada.
     * @param autoresIds   la lista de IDs de los autores.
     */
    public LibroDTO(Long id, String titulo, Long editorialId, List<Long> autoresIds) {
        this.id = id;
        this.titulo = titulo;
        this.editorialId = editorialId;
        this.autoresIds = autoresIds;
    }

    /**
     * Obtiene el identificador único del libro.
     *
     * @return el ID del libro.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del libro.
     *
     * @param id el ID a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return el título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo el título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el identificador de la editorial asociada al libro.
     *
     * @return el ID de la editorial.
     */
    public Long getEditorialId() {
        return editorialId;
    }

    /**
     * Establece el identificador de la editorial.
     *
     * @param editorialId el ID de la editorial a asignar.
     */
    public void setEditorialId(Long editorialId) {
        this.editorialId = editorialId;
    }

    /**
     * Obtiene la lista de identificadores de los autores asociados al libro.
     *
     * @return la lista de IDs de los autores.
     */
    public List<Long> getAutoresIds() {
        return autoresIds;
    }

    /**
     * Establece la lista de identificadores de los autores.
     *
     * @param autoresIds la lista de IDs a asignar.
     */
    public void setAutoresIds(List<Long> autoresIds) {
        this.autoresIds = autoresIds;
    }

    /**
     * Obtiene la cantidad de ejemplares disponibles del libro.
     *
     * @return el stock del libro.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de ejemplares disponibles del libro.
     *
     * @param stock la cantidad de stock a asignar.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
