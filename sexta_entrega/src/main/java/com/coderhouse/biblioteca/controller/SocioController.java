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

import com.coderhouse.biblioteca.dto.SocioDTO;
import com.coderhouse.biblioteca.service.SocioService;

/**
 * Controlador REST para manejar operaciones de Socios usando SocioDTO.
 */
@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    // GET /api/socios
    @GetMapping
    public List<SocioDTO> listarSocios() {
        return socioService.obtenerTodos();
    }

    // GET /api/socios/{id}
    @GetMapping("/{id}")
    public SocioDTO obtenerSocioPorId(@PathVariable Long id) {
        return socioService.obtenerPorId(id);
    }

    // POST /api/socios
    @PostMapping
    public SocioDTO crearSocio(@RequestBody SocioDTO socioDTO) {
        return socioService.guardar(socioDTO);
    }

    // PUT /api/socios/{id}
    @PutMapping("/{id}")
    public SocioDTO actualizarSocio(@PathVariable Long id, @RequestBody SocioDTO socioDTO) {
        // Verificamos si existe
        SocioDTO existente = socioService.obtenerPorId(id);
        if (existente == null) {
            return null; // o lanzar excepción
        }

        // Actualizar los datos
        existente.setNombre(socioDTO.getNombre());

        // Guardar y retornar
        return socioService.guardar(existente);
    }

    // DELETE /api/socios/{id}
    @DeleteMapping("/{id}")
    public void eliminarSocio(@PathVariable Long id) {
        socioService.eliminar(id);
    }
}
