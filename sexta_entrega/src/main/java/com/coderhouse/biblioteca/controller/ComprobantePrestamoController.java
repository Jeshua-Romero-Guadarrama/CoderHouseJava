package com.coderhouse.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.biblioteca.dto.ComprobantePrestamoDTO;
import com.coderhouse.biblioteca.model.ComprobantePrestamo;
import com.coderhouse.biblioteca.service.ComprobantePrestamoService;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobantePrestamoController {

    @Autowired
    private ComprobantePrestamoService comprobanteService;

    /**
     * Endpoint para crear un "comprobante" de préstamo múltiple. POST ->
     * /api/comprobantes
     */
    @PostMapping
    public ResponseEntity<?> crearComprobante(@RequestBody ComprobantePrestamoDTO dto) {
        try {
            // Llamar al servicio y retornar el comprobante si todo va bien.
            ComprobantePrestamo comprobante = comprobanteService.crearComprobante(dto);
            return ResponseEntity.ok(comprobante);

        } catch (Exception e) {
            // Si se lanza "No hay stock suficiente..." u otro error, regresamos HTTP 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}