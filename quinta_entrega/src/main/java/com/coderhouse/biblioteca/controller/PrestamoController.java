package com.coderhouse.biblioteca.controller;

import com.coderhouse.biblioteca.model.Prestamo;
import com.coderhouse.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con Préstamos.
 */
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Prestamo obtenerPrestamoPorId(@PathVariable Long id) {
        return prestamoService.obtenerPorId(id);
    }

    @PostMapping
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }

    @PutMapping("/{id}")
    public Prestamo actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Prestamo existente = prestamoService.obtenerPorId(id);
        if (existente != null) {
            existente.setSocio(prestamo.getSocio());
            existente.setLibro(prestamo.getLibro());
            existente.setFechaPrestamo(prestamo.getFechaPrestamo());
            existente.setFechaDevolucion(prestamo.getFechaDevolucion());
            return prestamoService.guardar(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}
