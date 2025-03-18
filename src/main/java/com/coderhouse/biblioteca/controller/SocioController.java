package com.coderhouse.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.biblioteca.dto.SocioDTO;
import com.coderhouse.biblioteca.service.SocioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;

/**
 * Controlador REST para gestionar operaciones relacionadas con Socios.
 * <p>
 * Este controlador permite realizar operaciones CRUD sobre la entidad Socio
 * utilizando objetos de transferencia de datos (DTO).
 * </p>
 */
@Tag(name = "Socios", description = "Endpoints para la gestión de socios en la biblioteca")
@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    /**
     * Obtiene la lista de todos los socios registrados.
     *
     * @return Lista de SocioDTO.
     */
    @Operation(summary = "Listar Socios", description = "Retorna una lista de todos los socios registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de socios obtenida exitosamente",
                 content = @Content(mediaType = "application/json",
                 array = @ArraySchema(schema = @Schema(implementation = SocioDTO.class))))
    @GetMapping
    public List<SocioDTO> listarSocios() {
        return socioService.obtenerTodos();
    }

    /**
     * Obtiene la información de un socio específico según su ID.
     *
     * @param id ID del socio a consultar.
     * @return SocioDTO con la información del socio.
     */
    @Operation(summary = "Obtener Socio", description = "Retorna la información de un socio según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Socio encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = SocioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Socio no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public SocioDTO obtenerSocioPorId(
            @Parameter(description = "ID del socio a consultar", required = true) @PathVariable Long id) {
        return socioService.obtenerPorId(id);
    }

    /**
     * Crea un nuevo socio en el sistema.
     *
     * @param socioDTO Objeto SocioDTO con los datos del nuevo socio.
     * @return SocioDTO del socio creado.
     */
    @Operation(summary = "Crear Socio", description = "Crea un nuevo socio utilizando los datos enviados")
    @ApiResponse(responseCode = "200", description = "Socio creado exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = SocioDTO.class)))
    @PostMapping
    public SocioDTO crearSocio(
            @Parameter(description = "Datos del socio a crear", required = true) @RequestBody SocioDTO socioDTO) {
        return socioService.guardar(socioDTO);
    }

    /**
     * Actualiza la información de un socio existente.
     *
     * @param id       ID del socio a actualizar.
     * @param socioDTO Objeto SocioDTO con la nueva información.
     * @return SocioDTO actualizado.
     */
    @Operation(summary = "Actualizar Socio", description = "Actualiza la información de un socio existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Socio actualizado exitosamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = SocioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Socio no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public SocioDTO actualizarSocio(
            @Parameter(description = "ID del socio a actualizar", required = true) @PathVariable Long id,
            @Parameter(description = "Nuevos datos del socio", required = true) @RequestBody SocioDTO socioDTO) {
        // Verificamos si el socio existe en el sistema
        SocioDTO existente = socioService.obtenerPorId(id);
        if (existente == null) {
            // Se puede lanzar una excepción personalizada si se desea
            return null;
        }
        // Actualizamos el nombre del socio con los datos nuevos
        existente.setNombre(socioDTO.getNombre());
        // Guardamos y retornamos el socio actualizado
        return socioService.guardar(existente);
    }

    /**
     * Elimina un socio del sistema según su ID.
     *
     * @param id ID del socio a eliminar.
     */
    @Operation(summary = "Eliminar Socio", description = "Elimina un socio del sistema utilizando su ID")
    @ApiResponse(responseCode = "200", description = "Socio eliminado exitosamente", content = @Content)
    @DeleteMapping("/{id}")
    public void eliminarSocio(
            @Parameter(description = "ID del socio a eliminar", required = true) @PathVariable Long id) {
        socioService.eliminar(id);
    }
}
