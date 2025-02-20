package com.ejemplo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.Cliente;
import com.ejemplo.service.ClienteService;

/**
 * Controlador REST para manejar las peticiones sobre el cliente.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Endpoint GET que retorna un JSON con:
     * {
     *   "nombre": XXXXXX,
     *   "apellido": YYYYYY,
     *   "años": #####
     * }
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable("id") Long id) {
        // Se busca el cliente en la BD
        Optional<Cliente> clienteOpt = clienteService.obtenerClientePorId(id);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            int edad = clienteService.calcularEdad(cliente.getFechaNacimiento());

            // Se estructura la respuesta en un Map para que se convierta a JSON automáticamente
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre", cliente.getNombre());
            respuesta.put("apellido", cliente.getApellido());
            respuesta.put("años", edad);

            return ResponseEntity.ok(respuesta);
        } else {
            // Si el cliente no existe, se retorna un 404
            return ResponseEntity.notFound().build();
        }
    }
}
