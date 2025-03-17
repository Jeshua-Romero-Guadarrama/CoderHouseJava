package com.coderhouse.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.biblioteca.dto.Mapper;
import com.coderhouse.biblioteca.dto.SocioDTO;
import com.coderhouse.biblioteca.model.Socio;
import com.coderhouse.biblioteca.repository.SocioRepository;

/**
 * Servicio para manejar la lógica de negocio de Socios usando SocioDTO.
 */
@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    /**
     * Retornar todos los socios como SocioDTO.
     */
    public List<SocioDTO> obtenerTodos() {
        return socioRepository.findAll()
                .stream()
                .map(Mapper::socioToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retornar un SocioDTO por id.
     */
    public SocioDTO obtenerPorId(Long id) {
        return socioRepository.findById(id)
                .map(Mapper::socioToDTO)
                .orElse(null);
    }

    /**
     * Guardar (crear o actualizar) un Socio a partir de SocioDTO.
     */
    public SocioDTO guardar(SocioDTO socioDTO) {
        // Convertir DTO a Entidad
        Socio socio = Mapper.dtoToSocio(socioDTO);

        // Guardar en BD
        Socio socioGuardado = socioRepository.save(socio);

        // Retornar como DTO
        return Mapper.socioToDTO(socioGuardado);
    }

    /**
     * Eliminar un Socio por ID.
     */
    public void eliminar(Long id) {
        socioRepository.deleteById(id);
    }
}
