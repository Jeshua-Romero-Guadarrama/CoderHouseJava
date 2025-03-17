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

import com.coderhouse.biblioteca.dto.LibroDTO;
import com.coderhouse.biblioteca.service.LibroService;

/**
 * Controlador REST para manejar las operaciones relacionadas con Libros (usando LibroDTO).
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // GET /api/libros
    @GetMapping
    public List<LibroDTO> listarLibros() {
        return libroService.obtenerTodos();
    }

    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public LibroDTO obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    // POST /api/libros
    @PostMapping
    public LibroDTO crearLibro(@RequestBody LibroDTO libroDTO) {
        return libroService.guardar(libroDTO);
    }

    // PUT /api/libros/{id}
    @PutMapping("/{id}")
    public LibroDTO actualizarLibro(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
        // Primero vemos si existe
        LibroDTO existente = libroService.obtenerPorId(id);
        if (existente == null) {
            return null; // o lanzar excepción
        }

        // Actualizar datos en el DTO existente
        existente.setTitulo(libroDTO.getTitulo());
        existente.setEditorialId(libroDTO.getEditorialId());
        existente.setAutoresIds(libroDTO.getAutoresIds());

        // Guardar y retornar
        return libroService.guardar(existente);
    }

    // DELETE /api/libros/{id}
    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}
