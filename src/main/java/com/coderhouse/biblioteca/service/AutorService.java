package com.coderhouse.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.biblioteca.dto.AutorDTO;
import com.coderhouse.biblioteca.dto.Mapper;
import com.coderhouse.biblioteca.model.Autor;
import com.coderhouse.biblioteca.repository.AutorRepository;

/**
 * Servicio para manejar la lógica de Autores.
 */
@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Devuelve una lista de todos los autores en forma de AutorDTO.
     */
    public List<AutorDTO> obtenerTodos() {
        return autorRepository.findAll()
                .stream()
                .map(Mapper::autorToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve un AutorDTO a partir de un ID de autor.
     */
    public AutorDTO obtenerPorId(Long id) {
        return autorRepository.findById(id)
                .map(Mapper::autorToDTO)
                .orElse(null);
    }

    /**
     * Guarda (crea o actualiza) un autor a partir de un AutorDTO.
     * Retorna el DTO con el ID asignado (en caso de ser nuevo).
     */
    public AutorDTO guardar(AutorDTO autorDTO) {
        // Convertimos DTO a entidad
        Autor autor = Mapper.dtoToAutor(autorDTO);

        // Guardamos en BD
        Autor autorGuardado = autorRepository.save(autor);

        // Retornamos como DTO
        return Mapper.autorToDTO(autorGuardado);
    }

    /**
     * Elimina un autor por ID.
     */
    public void eliminar(Long id) {
        autorRepository.deleteById(id);
    }
}
