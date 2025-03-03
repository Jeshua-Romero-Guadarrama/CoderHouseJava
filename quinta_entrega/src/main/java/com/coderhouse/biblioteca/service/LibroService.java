package com.coderhouse.biblioteca.service;

import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar lógica de negocio relacionada con Libros.
 */
@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        Optional<Libro> libroOpt = libroRepository.findById(id);
        return libroOpt.orElse(null);
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}
