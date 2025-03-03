package com.coderhouse.biblioteca.service;

import com.coderhouse.biblioteca.model.Socio;
import com.coderhouse.biblioteca.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio de los Socios.
 */
@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    public List<Socio> obtenerTodos() {
        return socioRepository.findAll();
    }

    public Socio obtenerPorId(Long id) {
        Optional<Socio> socioOpt = socioRepository.findById(id);
        return socioOpt.orElse(null);
    }

    public Socio guardar(Socio socio) {
        return socioRepository.save(socio);
    }

    public void eliminar(Long id) {
        socioRepository.deleteById(id);
    }
}
