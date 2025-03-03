package com.coderhouse.biblioteca.controller;

import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con Libros.
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Libro obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro existente = libroService.obtenerPorId(id);
        if (existente != null) {
            existente.setTitulo(libro.getTitulo());
            existente.setEditorial(libro.getEditorial());
            existente.setAutores(libro.getAutores());
            return libroService.guardar(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}
