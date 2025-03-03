package com.coderhouse.biblioteca.controller;

import com.coderhouse.biblioteca.model.Editorial;
import com.coderhouse.biblioteca.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con Editoriales.
 */
@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public List<Editorial> listarEditoriales() {
        return editorialService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Editorial obtenerEditorialPorId(@PathVariable Long id) {
        return editorialService.obtenerPorId(id);
    }

    @PostMapping
    public Editorial crearEditorial(@RequestBody Editorial editorial) {
        return editorialService.guardar(editorial);
    }

    @PutMapping("/{id}")
    public Editorial actualizarEditorial(@PathVariable Long id, @RequestBody Editorial editorial) {
        Editorial existente = editorialService.obtenerPorId(id);
        if (existente != null) {
            existente.setNombre(editorial.getNombre());
            return editorialService.guardar(existente);
        }
        return null; 
    }

    @DeleteMapping("/{id}")
    public void eliminarEditorial(@PathVariable Long id) {
        editorialService.eliminar(id);
    }
}
