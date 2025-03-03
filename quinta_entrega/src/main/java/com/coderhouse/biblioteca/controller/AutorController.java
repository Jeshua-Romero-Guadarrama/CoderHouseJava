package com.coderhouse.biblioteca.controller;

import com.coderhouse.biblioteca.model.Autor;
import com.coderhouse.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con Autores.
 */
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    /**
     * Endpoint para obtener la lista de todos los autores.
     * GET -> /api/autores
     */
    @GetMapping
    public List<Autor> listarAutores() {
        return autorService.obtenerTodos();
    }

    /**
     * Endpoint para obtener un autor por su ID.
     * GET -> /api/autores/{id}
     */
    @GetMapping("/{id}")
    public Autor obtenerAutorPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id);
    }

    /**
     * Endpoint para crear un autor nuevo.
     * POST -> /api/autores
     */
    @PostMapping
    public Autor crearAutor(@RequestBody Autor autor) {
        return autorService.guardar(autor);
    }

    /**
     * Endpoint para actualizar un autor existente.
     * PUT -> /api/autores/{id}
     */
    @PutMapping("/{id}")
    public Autor actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor existente = autorService.obtenerPorId(id);
        if (existente != null) {
            existente.setNombre(autor.getNombre());
            existente.setNacionalidad(autor.getNacionalidad());
            return autorService.guardar(existente);
        }
        return null; // Manejar caso de no encontrado
    }

    /**
     * Endpoint para eliminar un autor por su ID.
     * DELETE -> /api/autores/{id}
     */
    @DeleteMapping("/{id}")
    public void eliminarAutor(@PathVariable Long id) {
        autorService.eliminar(id);
    }
}
