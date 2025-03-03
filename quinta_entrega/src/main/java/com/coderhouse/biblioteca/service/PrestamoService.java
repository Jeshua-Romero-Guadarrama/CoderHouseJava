package com.coderhouse.biblioteca.service;

import com.coderhouse.biblioteca.model.Prestamo;
import com.coderhouse.biblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio de los Préstamos.
 */
@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPorId(Long id) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);
        return prestamoOpt.orElse(null);
    }

    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }
}
