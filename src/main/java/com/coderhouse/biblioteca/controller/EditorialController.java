package com.coderhouse.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.biblioteca.dto.EditorialDTO;
import com.coderhouse.biblioteca.service.EditorialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador REST para gestionar operaciones relacionadas con Editoriales.
 * <p>
 * Utiliza EditorialDTO para la transferencia de datos y permite realizar
 * operaciones CRUD (crear, leer, actualizar y eliminar) sobre las editoriales.
 * </p>
 */
@Tag(name = "Editoriales", description = "Endpoints para la gestión de editoriales en la biblioteca")
@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    /**
     * Obtiene la lista de todas las editoriales registradas.
     *
     * @return Lista de EditorialDTO que representa las editoriales.
     */
    @Operation(summary = "Listar Editoriales", description = "Retorna la lista completa de editoriales registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de editoriales obtenida exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = EditorialDTO.class)))
    @GetMapping
    public List<EditorialDTO> listarEditoriales() {
        return editorialService.obtenerTodas();
    }

    /**
     * Obtiene los detalles de una editorial a partir de su ID.
     *
     * @param id Identificador de la editorial a consultar.
     * @return EditorialDTO con la información de la editorial.
     */
    @Operation(summary = "Obtener Editorial", description = "Retorna los detalles de una editorial a partir de su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Editorial encontrada",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EditorialDTO.class))),
        @ApiResponse(responseCode = "404", description = "Editorial no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public EditorialDTO obtenerEditorialPorId(
            @Parameter(description = "ID de la editorial", required = true) @PathVariable Long id) {
        return editorialService.obtenerPorId(id);
    }

    /**
     * Crea una nueva editorial utilizando la información proporcionada.
     *
     * @param editorialDTO Objeto EditorialDTO con los datos de la nueva editorial.
     * @return EditorialDTO del registro creado.
     */
    @Operation(summary = "Crear Editorial", description = "Crea una nueva editorial con los datos enviados en el objeto EditorialDTO")
    @ApiResponse(responseCode = "200", description = "Editorial creada exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = EditorialDTO.class)))
    @PostMapping
    public EditorialDTO crearEditorial(
            @Parameter(description = "Datos de la nueva editorial", required = true) @RequestBody EditorialDTO editorialDTO) {
        return editorialService.guardar(editorialDTO);
    }

    /**
     * Actualiza la información de una editorial existente.
     *
     * @param id           Identificador de la editorial a actualizar.
     * @param editorialDTO Objeto EditorialDTO con los nuevos datos.
     * @return EditorialDTO actualizado.
     */
    @Operation(summary = "Actualizar Editorial", description = "Actualiza los datos de una editorial existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Editorial actualizada exitosamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EditorialDTO.class))),
        @ApiResponse(responseCode = "404", description = "Editorial no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public EditorialDTO actualizarEditorial(
            @Parameter(description = "ID de la editorial a actualizar", required = true) @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la editorial", required = true) @RequestBody EditorialDTO editorialDTO) {
        // Verificar si la editorial existe en el sistema
        EditorialDTO existente = editorialService.obtenerPorId(id);
        if (existente == null) {
            // Aquí se podría lanzar una excepción personalizada si se prefiere
            return null;
        }

        // Actualizar el nombre de la editorial
        existente.setNombre(editorialDTO.getNombre());

        // Guardar y retornar la editorial actualizada
        return editorialService.guardar(existente);
    }

    /**
     * Elimina una editorial a partir de su ID.
     *
     * @param id Identificador de la editorial a eliminar.
     */
    @Operation(summary = "Eliminar Editorial", description = "Elimina una editorial del sistema según su ID")
    @ApiResponse(responseCode = "200", description = "Editorial eliminada exitosamente", content = @Content)
    @DeleteMapping("/{id}")
    public void eliminarEditorial(
            @Parameter(description = "ID de la editorial a eliminar", required = true) @PathVariable Long id) {
        editorialService.eliminar(id);
    }
}
