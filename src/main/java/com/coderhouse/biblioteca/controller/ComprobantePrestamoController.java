package com.coderhouse.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.biblioteca.dto.ComprobantePrestamoDTO;
import com.coderhouse.biblioteca.model.ComprobantePrestamo;
import com.coderhouse.biblioteca.service.ComprobantePrestamoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador REST para gestionar comprobantes de préstamo múltiple.
 * <p>
 * Este controlador expone el endpoint para la creación de comprobantes de préstamo,
 * utilizando ComprobantePrestamoDTO para la transferencia de datos.
 * </p>
 */
@Tag(name = "Comprobantes", description = "Endpoints para la gestión de comprobantes de préstamo múltiple")
@RestController
@RequestMapping("/api/comprobantes")
public class ComprobantePrestamoController {

    @Autowired
    private ComprobantePrestamoService comprobanteService;

    /**
     * Crea un comprobante de préstamo múltiple a partir de la información proporcionada.
     * <p>
     * Endpoint: POST /api/comprobantes
     * </p>
     *
     * @param dto Objeto ComprobantePrestamoDTO que contiene la información del préstamo múltiple.
     * @return ResponseEntity que retorna el comprobante creado o un mensaje de error.
     */
    @Operation(
        summary = "Crear comprobante de préstamo",
        description = "Crea un comprobante de préstamo múltiple a partir de la información enviada en ComprobantePrestamoDTO"
    )
    @ApiResponse(responseCode = "200", description = "Comprobante creado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComprobantePrestamo.class)))
    @ApiResponse(responseCode = "400", description = "Error en la creación del comprobante", content = @Content)
    @PostMapping
    public ResponseEntity<?> crearComprobante(
            @Parameter(description = "Objeto ComprobantePrestamoDTO con los datos del préstamo múltiple", required = true)
            @RequestBody ComprobantePrestamoDTO dto) {
        try {
            // Llamada al servicio para crear el comprobante de préstamo
            ComprobantePrestamo comprobante = comprobanteService.crearComprobante(dto);
            return ResponseEntity.ok(comprobante);
        } catch (Exception e) {
            // En caso de error, retorna un HTTP 400 con el mensaje de la excepción
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
