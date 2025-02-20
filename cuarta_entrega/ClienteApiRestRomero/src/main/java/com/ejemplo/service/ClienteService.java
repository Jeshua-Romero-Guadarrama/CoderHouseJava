package com.ejemplo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.model.Cliente;
import com.ejemplo.repository.ClienteRepository;

/**
 * Servicio que maneja la lógica de negocio para los clientes.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Se busca el cliente por su ID y retorna un Optional de Cliente.
     */
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Calcula la edad basándose en la fecha de nacimiento.
     */
    public int calcularEdad(LocalDate fechaNacimiento) {
        // Usamos la clase Period para calcular la diferencia entre la fecha de nacimiento y la fecha actual
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}
