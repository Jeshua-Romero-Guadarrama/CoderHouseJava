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

import com.coderhouse.biblioteca.dto.EditorialDTO;
import com.coderhouse.biblioteca.service.EditorialService;

/**
 * Controlador REST para manejar operaciones de Editoriales a través de
 * EditorialDTO.
 */
@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    // GET /api/editoriales
    @GetMapping
    public List<EditorialDTO> listarEditoriales() {
        return editorialService.obtenerTodas();
    }

    // GET /api/editoriales/{id}
    @GetMapping("/{id}")
    public EditorialDTO obtenerEditorialPorId(@PathVariable Long id) {
        return editorialService.obtenerPorId(id);
    }

    // POST /api/editoriales
    @PostMapping
    public EditorialDTO crearEditorial(@RequestBody EditorialDTO editorialDTO) {
        return editorialService.guardar(editorialDTO);
    }

    // PUT /api/editoriales/{id}
    @PutMapping("/{id}")
    public EditorialDTO actualizarEditorial(@PathVariable Long id, @RequestBody EditorialDTO editorialDTO) {
        // Verificar si existe
        EditorialDTO existente = editorialService.obtenerPorId(id);
        if (existente == null) {
            return null; // o lanzar excepción
        }

        // Actualizar los datos
        existente.setNombre(editorialDTO.getNombre());

        // Guardar y retornar
        return editorialService.guardar(existente);
    }

    // DELETE /api/editoriales/{id}
    @DeleteMapping("/{id}")
    public void eliminarEditorial(@PathVariable Long id) {
        editorialService.eliminar(id);
    }
}
