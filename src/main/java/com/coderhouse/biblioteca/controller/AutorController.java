package com.coderhouse.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.biblioteca.dto.AutorDTO;
import com.coderhouse.biblioteca.service.AutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador REST para manejar operaciones de Autores mediante AutorDTO.
 * 
 * Se utiliza Swagger/OpenAPI para documentar cada endpoint y sus respuestas.
 */
@Tag(name = "Autores", description = "Endpoints para la gestión de autores")
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    /**
     * GET /api/autores
     * 
     * Retorna una lista de todos los autores registrados.
     * 
     * @return Lista de AutorDTO.
     */
    @Operation(
        summary = "Listar todos los autores", 
        description = "Retorna una lista de todos los autores registrados en el sistema",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorDTO.class)))
        }
    )
    @GetMapping
    public List<AutorDTO> listarAutores() {
        return autorService.obtenerTodos();
    }

    /**
     * GET /api/autores/{id}
     * 
     * Retorna el autor correspondiente al ID proporcionado.
     * 
     * @param id ID del autor a buscar.
     * @return AutorDTO si se encuentra; de lo contrario, null.
     */
    @Operation(
        summary = "Obtener autor por ID", 
        description = "Retorna el autor que coincide con el ID proporcionado",
        responses = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content)
        }
    )
    @GetMapping("/{id}")
    public AutorDTO obtenerAutorPorId(
            @Parameter(description = "ID del autor a buscar", required = true) @PathVariable Long id) {
        return autorService.obtenerPorId(id);
    }

    /**
     * POST /api/autores
     * 
     * Crea un nuevo autor a partir de los datos recibidos.
     * 
     * @param autorDTO Objeto AutorDTO con la información del nuevo autor.
     * @return AutorDTO creado con su ID asignado.
     */
    @Operation(
        summary = "Crear un nuevo autor", 
        description = "Crea y guarda un nuevo autor en la base de datos a partir del objeto AutorDTO recibido",
        responses = {
            @ApiResponse(responseCode = "200", description = "Autor creado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
        }
    )
    @PostMapping
    public AutorDTO crearAutor(
            @Parameter(description = "Objeto AutorDTO con los datos del nuevo autor", required = true) 
            @RequestBody AutorDTO autorDTO) {
        return autorService.guardar(autorDTO);
    }

    /**
     * PUT /api/autores/{id}
     * 
     * Actualiza la información de un autor existente identificado por su ID.
     * 
     * @param id       ID del autor a actualizar.
     * @param autorDTO Objeto AutorDTO con los nuevos datos.
     * @return AutorDTO actualizado o null si no se encuentra el autor.
     */
    @Operation(
        summary = "Actualizar un autor existente", 
        description = "Actualiza los datos de un autor ya existente, identificado por su ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Autor actualizado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content)
        }
    )
    @PutMapping("/{id}")
    public AutorDTO actualizarAutor(
            @Parameter(description = "ID del autor a actualizar", required = true) @PathVariable Long id,
            @Parameter(description = "Objeto AutorDTO con los nuevos datos del autor", required = true) @RequestBody AutorDTO autorDTO) {
        // Verificar si el autor existe
        AutorDTO existente = autorService.obtenerPorId(id);
        if (existente == null) {
            // Aquí se podría lanzar una excepción o retornar un error
            return null;
        }
        // Actualizar los datos en el DTO existente
        existente.setNombre(autorDTO.getNombre());
        existente.setNacionalidad(autorDTO.getNacionalidad());
        // Guardar y retornar el autor actualizado
        return autorService.guardar(existente);
    }

    /**
     * DELETE /api/autores/{id}
     * 
     * Elimina el autor identificado por el ID proporcionado.
     * 
     * @param id ID del autor a eliminar.
     */
    @Operation(
        summary = "Eliminar un autor", 
        description = "Elimina un autor del sistema identificado por su ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Autor eliminado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content)
        }
    )
    @DeleteMapping("/{id}")
    public void eliminarAutor(
            @Parameter(description = "ID del autor a eliminar", required = true) @PathVariable Long id) {
        autorService.eliminar(id);
    }
}
