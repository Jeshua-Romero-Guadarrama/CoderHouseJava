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

import com.coderhouse.biblioteca.dto.AutorDTO;
import com.coderhouse.biblioteca.service.AutorService;

/**
 * Controlador REST para manejar operaciones de Autores vía AutorDTO.
 */
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // GET /api/autores
    @GetMapping
    public List<AutorDTO> listarAutores() {
        return autorService.obtenerTodos();
    }

    // GET /api/autores/{id}
    @GetMapping("/{id}")
    public AutorDTO obtenerAutorPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id);
    }

    // POST /api/autores
    @PostMapping
    public AutorDTO crearAutor(@RequestBody AutorDTO autorDTO) {
        return autorService.guardar(autorDTO);
    }

    // PUT /api/autores/{id}
    @PutMapping("/{id}")
    public AutorDTO actualizarAutor(@PathVariable Long id, @RequestBody AutorDTO autorDTO) {
        // Verificar si existe
        AutorDTO existente = autorService.obtenerPorId(id);
        if (existente == null) {
            // Lanzar excepción
            return null;
        }

        // Actualizar los datos en el DTO existente
        existente.setNombre(autorDTO.getNombre());
        existente.setNacionalidad(autorDTO.getNacionalidad());

        // Guardamr y retornar
        return autorService.guardar(existente);
    }

    // DELETE /api/autores/{id}
    @DeleteMapping("/{id}")
    public void eliminarAutor(@PathVariable Long id) {
        autorService.eliminar(id);
    }
}
