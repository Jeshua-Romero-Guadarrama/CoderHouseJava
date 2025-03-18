package com.coderhouse.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.biblioteca.dto.PrestamoDTO;
import com.coderhouse.biblioteca.service.PrestamoService;

/**
 * Controlador REST para manejar las operaciones de Préstamos (usando PrestamoDTO).
 */
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    // GET /api/prestamos
    @GetMapping
    public List<PrestamoDTO> listarPrestamos() {
        return prestamoService.obtenerTodos();
    }

    // GET /api/prestamos/{id}
    @GetMapping("/{id}")
    public PrestamoDTO obtenerPrestamoPorId(@PathVariable Long id) {
        return prestamoService.obtenerPorId(id);
    }

    // POST /api/prestamos
    @PostMapping
    public PrestamoDTO crearPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        return prestamoService.guardar(prestamoDTO);
    }

    // PUT /api/prestamos/{id}
    @PutMapping("/{id}")
    public PrestamoDTO actualizarPrestamo(@PathVariable Long id, @RequestBody PrestamoDTO prestamoDTO) {
        // Verificar si existe
        PrestamoDTO existente = prestamoService.obtenerPorId(id);
        if (existente == null) {
            // Lanzar excepción
            return null; 
        }

        // Actualizar la información
        existente.setSocioId(prestamoDTO.getSocioId());
        existente.setLibroId(prestamoDTO.getLibroId());
        existente.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        existente.setFechaDevolucion(prestamoDTO.getFechaDevolucion());

        // Guardar y retornar
        return prestamoService.guardar(existente);
    }

    // DELETE /api/prestamos/{id}
    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}
