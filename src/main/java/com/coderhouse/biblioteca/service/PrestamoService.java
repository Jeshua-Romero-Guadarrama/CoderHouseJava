package com.coderhouse.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.biblioteca.dto.Mapper;
import com.coderhouse.biblioteca.dto.PrestamoDTO;
import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.model.Prestamo;
import com.coderhouse.biblioteca.model.Socio;
import com.coderhouse.biblioteca.repository.LibroRepository;
import com.coderhouse.biblioteca.repository.PrestamoRepository;
import com.coderhouse.biblioteca.repository.SocioRepository;

/**
 * Servicio para manejar la lógica de negocio de Préstamos usando PrestamoDTO.
 */
@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private LibroRepository libroRepository;

    /**
     * Retornar todos los préstamos como PrestamoDTO.
     */
    public List<PrestamoDTO> obtenerTodos() {
        return prestamoRepository.findAll()
                .stream()
                .map(Mapper::prestamoToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retornar un préstamo por id como PrestamoDTO.
     */
    public PrestamoDTO obtenerPorId(Long id) {
        return prestamoRepository.findById(id)
                .map(Mapper::prestamoToDTO)
                .orElse(null);
    }

    /**
     * Guardar (crear o actualizar) un Préstamo a partir de PrestamoDTO.
     */
    public PrestamoDTO guardar(PrestamoDTO prestamoDTO) {
        // Convertir de DTO a Entidad
        Prestamo prestamo = Mapper.dtoToPrestamo(prestamoDTO);

        // Asignar el socio si existe
        if (prestamoDTO.getSocioId() != null) {
            Optional<Socio> socioOpt = socioRepository.findById(prestamoDTO.getSocioId());
            socioOpt.ifPresent(prestamo::setSocio);
        }

        // Asignar el libro si existe
        if (prestamoDTO.getLibroId() != null) {
            Optional<Libro> libroOpt = libroRepository.findById(prestamoDTO.getLibroId());
            libroOpt.ifPresent(prestamo::setLibro);
        }

        // Guardar en BD
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        // Retornar DTO
        return Mapper.prestamoToDTO(prestamoGuardado);
    }

    /**
     * Eliminar un préstamo por ID.
     */
    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }
}
