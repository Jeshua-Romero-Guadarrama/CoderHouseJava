package com.coderhouse.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.biblioteca.dto.LibroDTO;
import com.coderhouse.biblioteca.dto.Mapper;
import com.coderhouse.biblioteca.model.Autor;
import com.coderhouse.biblioteca.model.Editorial;
import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.repository.AutorRepository;
import com.coderhouse.biblioteca.repository.EditorialRepository;
import com.coderhouse.biblioteca.repository.LibroRepository;

/**
 * Servicio para la lógica de negocio de Libros usando LibroDTO.
 */
@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Retornar todos los libros como LibroDTO.
     */
    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll()
                .stream()
                .map(Mapper::libroToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retornar un LibroDTO a partir de un ID de libro.
     */
    public LibroDTO obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .map(Mapper::libroToDTO)
                .orElse(null);
    }

    /**
     * Guarda (crea o actualiza) un Libro a partir de un LibroDTO.
     * - Asigna la Editorial y Autores correspondientes antes de guardar.
     */
    public LibroDTO guardar(LibroDTO libroDTO) {
        Libro libro;
        // Si el DTO tiene ID, se asume que es una actualización y se carga la entidad existente.
        if (libroDTO.getId() != null) {
            libro = libroRepository.findById(libroDTO.getId())
                      .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + libroDTO.getId()));
        } else {
            libro = new Libro();
        }
    
        // Actualizar campos simples
        libro.setTitulo(libroDTO.getTitulo());
    
        libro.setStock(libroDTO.getStock());
        // Actualizar la editorial: buscar por editorialId
        if (libroDTO.getEditorialId() != null) {
            Optional<Editorial> editorialOpt = editorialRepository.findById(libroDTO.getEditorialId());
            if (editorialOpt.isPresent()) {
                libro.setEditorial(editorialOpt.get());
            } else {
                throw new RuntimeException("Editorial no encontrada con id: " + libroDTO.getEditorialId());
            }
        } else {
            // Si no se proporciona un editorialId, se debe lanzar error, ya que es obligatorio.
            throw new RuntimeException("El campo editorialId es obligatorio.");
        }
    
        // Actualizar la lista de autores
        if (libroDTO.getAutoresIds() != null && !libroDTO.getAutoresIds().isEmpty()) {
            List<Autor> listaAutores = new ArrayList<>();
            for (Long autorId : libroDTO.getAutoresIds()) {
                Optional<Autor> autorOpt = autorRepository.findById(autorId);
                if (autorOpt.isPresent()) {
                    listaAutores.add(autorOpt.get());
                } else {
                    throw new RuntimeException("Autor no encontrado con id: " + autorId);
                }
            }
            libro.setAutores(listaAutores);
        } else {
            libro.setAutores(new ArrayList<>());
        }
    
        // Guardar la entidad actualizada
        Libro libroGuardado = libroRepository.save(libro);
        return Mapper.libroToDTO(libroGuardado);
    }    

    /**
     * Eliminar un libro por ID.
     */
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}
