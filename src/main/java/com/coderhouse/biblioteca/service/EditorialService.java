package com.coderhouse.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.biblioteca.dto.EditorialDTO;
import com.coderhouse.biblioteca.dto.Mapper;
import com.coderhouse.biblioteca.model.Editorial;
import com.coderhouse.biblioteca.repository.EditorialRepository;

/**
 * Servicio para manejar la lógica de Editoriales.
 */
@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    /**
     * Devuelve todas las editoriales como EditorialDTO.
     */
    public List<EditorialDTO> obtenerTodas() {
        return editorialRepository.findAll()
                .stream()
                .map(Mapper::editorialToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve una editorial por id como EditorialDTO.
     */
    public EditorialDTO obtenerPorId(Long id) {
        return editorialRepository.findById(id)
                .map(Mapper::editorialToDTO)
                .orElse(null);
    }

    /**
     * Guarda (crea o actualiza) una editorial a partir de un EditorialDTO.
     */
    public EditorialDTO guardar(EditorialDTO editorialDTO) {
        // Convertir de DTO a Entidad
        Editorial editorial = Mapper.dtoToEditorial(editorialDTO);

        // Guardar en la BD
        Editorial editorialGuardada = editorialRepository.save(editorial);

        // Retornar como DTO
        return Mapper.editorialToDTO(editorialGuardada);
    }

    /**
     * Eliminar una editorial por su ID.
     */
    public void eliminar(Long id) {
        editorialRepository.deleteById(id);
    }
}
