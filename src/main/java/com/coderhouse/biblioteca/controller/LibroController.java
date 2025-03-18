package com.coderhouse.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.biblioteca.dto.LibroDTO;
import com.coderhouse.biblioteca.service.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador REST para gestionar operaciones relacionadas con Libros.
 * <p>
 * Utiliza LibroDTO para la transferencia de datos y permite realizar operaciones
 * CRUD: listar, obtener, crear, actualizar y eliminar libros.
 * </p>
 */
@Tag(name = "Libros", description = "Endpoints para la gestión de libros en la biblioteca")
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    /**
     * Retorna la lista de todos los libros registrados.
     *
     * @return Lista de LibroDTO.
     */
    @Operation(summary = "Listar Libros", description = "Retorna una lista con todos los libros registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de libros obtenida exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = LibroDTO.class)))
    @GetMapping
    public List<LibroDTO> listarLibros() {
        return libroService.obtenerTodos();
    }

    /**
     * Retorna la información de un libro específico según su ID.
     *
     * @param id Identificador del libro.
     * @return LibroDTO con la información del libro.
     */
    @Operation(summary = "Obtener Libro", description = "Retorna los detalles de un libro según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LibroDTO.class))),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public LibroDTO obtenerLibroPorId(
            @Parameter(description = "ID del libro a consultar", required = true) @PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    /**
     * Crea un nuevo libro en el sistema utilizando la información proporcionada.
     *
     * @param libroDTO Objeto LibroDTO con los datos del nuevo libro.
     * @return LibroDTO del libro creado.
     */
    @Operation(summary = "Crear Libro", description = "Crea un nuevo libro en el sistema con los datos enviados")
    @ApiResponse(responseCode = "200", description = "Libro creado exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = LibroDTO.class)))
    @PostMapping
    public LibroDTO crearLibro(
            @Parameter(description = "Datos del libro a crear", required = true) @RequestBody LibroDTO libroDTO) {
        return libroService.guardar(libroDTO);
    }

    /**
     * Actualiza la información de un libro existente.
     *
     * @param id       Identificador del libro a actualizar.
     * @param libroDTO Objeto LibroDTO con los nuevos datos.
     * @return LibroDTO actualizado.
     */
    @Operation(summary = "Actualizar Libro", description = "Actualiza la información de un libro existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro actualizado exitosamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LibroDTO.class))),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public LibroDTO actualizarLibro(
            @Parameter(description = "ID del libro a actualizar", required = true) @PathVariable Long id,
            @Parameter(description = "Nuevos datos del libro", required = true) @RequestBody LibroDTO libroDTO) {
        // Primero se verifica si el libro existe
        LibroDTO existente = libroService.obtenerPorId(id);
        if (existente == null) {
            // Se puede lanzar una excepción personalizada si se prefiere
            return null;
        }

        // Actualizar los datos en el DTO existente
        existente.setTitulo(libroDTO.getTitulo());
        existente.setEditorialId(libroDTO.getEditorialId());
        existente.setAutoresIds(libroDTO.getAutoresIds());

        // Guardar y retornar el libro actualizado
        return libroService.guardar(existente);
    }

    /**
     * Elimina un libro del sistema según su ID.
     *
     * @param id Identificador del libro a eliminar.
     */
    @Operation(summary = "Eliminar Libro", description = "Elimina un libro del sistema según su ID")
    @ApiResponse(responseCode = "200", description = "Libro eliminado exitosamente", content = @Content)
    @DeleteMapping("/{id}")
    public void eliminarLibro(
            @Parameter(description = "ID del libro a eliminar", required = true) @PathVariable Long id) {
        libroService.eliminar(id);
    }
}
