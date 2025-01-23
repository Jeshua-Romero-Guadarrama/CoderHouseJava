package com.coderhouse.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.biblioteca.dto.PrestamoDTO;
import com.coderhouse.biblioteca.service.PrestamoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;

/**
 * Controlador REST para gestionar operaciones relacionadas con Préstamos.
 * <p>
 * Este controlador permite realizar operaciones CRUD sobre los préstamos, 
 * utilizando PrestamoDTO para la transferencia de datos.
 * </p>
 */
@Tag(name = "Préstamos", description = "Endpoints para la gestión de préstamos en la biblioteca")
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    /**
     * Retorna la lista de todos los préstamos registrados.
     *
     * @return Lista de PrestamoDTO.
     */
    @Operation(summary = "Listar Préstamos", description = "Retorna una lista de todos los préstamos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de préstamos obtenida exitosamente",
                 content = @Content(mediaType = "application/json",
                 array = @ArraySchema(schema = @Schema(implementation = PrestamoDTO.class))))
    @GetMapping
    public List<PrestamoDTO> listarPrestamos() {
        return prestamoService.obtenerTodos();
    }

    /**
     * Retorna la información de un préstamo específico según su ID.
     *
     * @param id Identificador del préstamo.
     * @return PrestamoDTO con la información del préstamo.
     */
    @Operation(summary = "Obtener Préstamo", description = "Retorna los detalles de un préstamo según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = PrestamoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public PrestamoDTO obtenerPrestamoPorId(
            @Parameter(description = "ID del préstamo a consultar", required = true) @PathVariable Long id) {
        return prestamoService.obtenerPorId(id);
    }

    /**
     * Crea un nuevo préstamo en el sistema utilizando los datos proporcionados.
     *
     * @param prestamoDTO Objeto PrestamoDTO con los datos del nuevo préstamo.
     * @return PrestamoDTO del préstamo creado.
     */
    @Operation(summary = "Crear Préstamo", description = "Crea un nuevo préstamo en el sistema con los datos enviados")
    @ApiResponse(responseCode = "200", description = "Préstamo creado exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = PrestamoDTO.class)))
    @PostMapping
    public PrestamoDTO crearPrestamo(
            @Parameter(description = "Datos del préstamo a crear", required = true) @RequestBody PrestamoDTO prestamoDTO) {
        return prestamoService.guardar(prestamoDTO);
    }

    /**
     * Actualiza la información de un préstamo existente.
     *
     * @param id          Identificador del préstamo a actualizar.
     * @param prestamoDTO Objeto PrestamoDTO con los nuevos datos.
     * @return PrestamoDTO actualizado.
     */
    @Operation(summary = "Actualizar Préstamo", description = "Actualiza la información de un préstamo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo actualizado exitosamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = PrestamoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public PrestamoDTO actualizarPrestamo(
            @Parameter(description = "ID del préstamo a actualizar", required = true) @PathVariable Long id,
            @Parameter(description = "Nuevos datos del préstamo", required = true) @RequestBody PrestamoDTO prestamoDTO) {
        // Verificar si el préstamo existe
        PrestamoDTO existente = prestamoService.obtenerPorId(id);
        if (existente == null) {
            // Se puede lanzar una excepción personalizada si se prefiere
            return null;
        }
        // Actualizar la información del préstamo
        existente.setSocioId(prestamoDTO.getSocioId());
        existente.setLibroId(prestamoDTO.getLibroId());
        existente.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        existente.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        // Guardar y retornar el préstamo actualizado
        return prestamoService.guardar(existente);
    }

    /**
     * Elimina un préstamo del sistema según su ID.
     *
     * @param id Identificador del préstamo a eliminar.
     */
    @Operation(summary = "Eliminar Préstamo", description = "Elimina un préstamo del sistema utilizando su ID")
    @ApiResponse(responseCode = "200", description = "Préstamo eliminado exitosamente", content = @Content)
    @DeleteMapping("/{id}")
    public void eliminarPrestamo(
            @Parameter(description = "ID del préstamo a eliminar", required = true) @PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}
