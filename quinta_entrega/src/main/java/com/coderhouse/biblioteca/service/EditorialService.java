package com.coderhouse.biblioteca.service;

import com.coderhouse.biblioteca.model.Editorial;
import com.coderhouse.biblioteca.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar lógica de negocio relacionada con Editoriales.
 */
@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public List<Editorial> obtenerTodas() {
        return editorialRepository.findAll();
    }

    public Editorial obtenerPorId(Long id) {
        Optional<Editorial> editorialOpt = editorialRepository.findById(id);
        return editorialOpt.orElse(null);
    }

    public Editorial guardar(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    public void eliminar(Long id) {
        editorialRepository.deleteById(id);
    }
}
