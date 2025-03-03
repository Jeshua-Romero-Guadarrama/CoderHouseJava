package com.coderhouse.biblioteca.service;

import com.coderhouse.biblioteca.model.Autor;
import com.coderhouse.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar lógica de negocio relacionada con Autores.
 */
@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Obtiene la lista completa de autores.
     */
    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    /**
     * Busca un autor por su ID.
     */
    public Autor obtenerPorId(Long id) {
        Optional<Autor> autorOpt = autorRepository.findById(id);
        return autorOpt.orElse(null);
    }

    /**
     * Crea o actualiza un autor en la base de datos.
     */
    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    /**
     * Elimina un autor por su ID.
     */
    public void eliminar(Long id) {
        autorRepository.deleteById(id);
    }
}
