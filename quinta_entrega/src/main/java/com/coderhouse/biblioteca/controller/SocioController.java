package com.coderhouse.biblioteca.controller;

import com.coderhouse.biblioteca.model.Socio;
import com.coderhouse.biblioteca.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con Socios.
 */
@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public List<Socio> listarSocios() {
        return socioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Socio obtenerSocioPorId(@PathVariable Long id) {
        return socioService.obtenerPorId(id);
    }

    @PostMapping
    public Socio crearSocio(@RequestBody Socio socio) {
        return socioService.guardar(socio);
    }

    @PutMapping("/{id}")
    public Socio actualizarSocio(@PathVariable Long id, @RequestBody Socio socio) {
        Socio existente = socioService.obtenerPorId(id);
        if (existente != null) {
            existente.setNombre(socio.getNombre());
            return socioService.guardar(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarSocio(@PathVariable Long id) {
        socioService.eliminar(id);
    }
}
