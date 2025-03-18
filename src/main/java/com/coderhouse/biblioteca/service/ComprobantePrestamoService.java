package com.coderhouse.biblioteca.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.biblioteca.dto.ComprobantePrestamoDTO;
import com.coderhouse.biblioteca.dto.DetallePrestamoDTO;
import com.coderhouse.biblioteca.model.ComprobantePrestamo;
import com.coderhouse.biblioteca.model.DetallePrestamo;
import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.model.Socio;
import com.coderhouse.biblioteca.repository.ComprobantePrestamoRepository;
import com.coderhouse.biblioteca.repository.DetallePrestamoRepository;
import com.coderhouse.biblioteca.repository.LibroRepository;
import com.coderhouse.biblioteca.repository.SocioRepository;

@Service
public class ComprobantePrestamoService {

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ComprobantePrestamoRepository comprobanteRepository;

    @Autowired
    private DetallePrestamoRepository detalleRepository;

    /**
     * Crea un comprobante de préstamo múltiple a partir del DTO de entrada.
     */
    public ComprobantePrestamo crearComprobante(ComprobantePrestamoDTO dto) throws Exception {

        // 1) Validar que el Socio exista
        if (dto.getSocio() == null || dto.getSocio().getId() == null) {
            throw new Exception("Socio inválido en la petición.");
        }
        Optional<Socio> socioOpt = socioRepository.findById(dto.getSocio().getId());
        if (!socioOpt.isPresent()) {
            throw new Exception("No existe el socio con id=" + dto.getSocio().getId());
        }
        Socio socio = socioOpt.get();

        // 2) Para cada linea, validar libro y stock
        List<DetallePrestamo> listaDetalles = new ArrayList<>();
        int totalDeLibros = 0;

        if (dto.getLineas() == null || dto.getLineas().isEmpty()) {
            throw new Exception("No se recibieron líneas de detalle.");
        }

        for (DetallePrestamoDTO lineaDTO : dto.getLineas()) {
            if (lineaDTO.getLibro() == null || lineaDTO.getLibro().getId() == null) {
                throw new Exception("Linea con libro inválido.");
            }
            Optional<Libro> libroOpt = libroRepository.findById(lineaDTO.getLibro().getId());
            if (!libroOpt.isPresent()) {
                throw new Exception("No existe el libro con id=" + lineaDTO.getLibro().getId());
            }
            Libro libro = libroOpt.get();

            int cantidadSolicitada = lineaDTO.getCantidad();
            if (cantidadSolicitada <= 0) {
                throw new Exception("Cantidad solicitada inválida para el libro: " + libro.getTitulo());
            }

            // Validar stock
            if (cantidadSolicitada > libro.getStock()) {
                throw new Exception("No hay stock suficiente del libro: "
                        + libro.getTitulo()
                        + ". Stock actual: " + libro.getStock());
            }

            // Descontar stock
            libro.setStock(libro.getStock() - cantidadSolicitada);
            libroRepository.save(libro);

            // Crear el detalle
            DetallePrestamo detalle = new DetallePrestamo();
            detalle.setLibro(libro);
            detalle.setCantidad(cantidadSolicitada);
            listaDetalles.add(detalle);

            totalDeLibros += cantidadSolicitada;
        }

        // 3) Obtener la fecha (intento desde servicio externo, si falla => LocalDate.now())
        LocalDate fechaComprobante = obtenerFechaDesdeServicioExterno();

        // 4) Crear y persistir el comprobante
        ComprobantePrestamo comprobante = new ComprobantePrestamo();
        comprobante.setSocio(socio);
        comprobante.setFechaComprobante(fechaComprobante);
        comprobante.setTotalLibros(totalDeLibros);

        // Primero guardar el comprobante (para obtener ID)
        comprobante = comprobanteRepository.save(comprobante);

        // 5) Vincular cada detalle con el comprobante y guardar
        for (DetallePrestamo det : listaDetalles) {
            det.setComprobante(comprobante);
            detalleRepository.save(det);
        }

        // 6) Cargar la lista de detalles en el comprobante
        comprobante.setDetalles(listaDetalles);

        return comprobante;
    }

    /**
     * Ejemplo para obtener la fecha desde un API externo (worldclockapi) Si
     * falla, usamos LocalDate.now().
     */
    private LocalDate obtenerFechaDesdeServicioExterno() {
        try {
            // Crear una instancia de RestTemplate
            RestTemplate restTemplate = new RestTemplate();
            // URL del servicio
            String url = "http://worldclockapi.com/api/json/utc/now";
            // Realizar la petición GET y recibir la respuesta como Map
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            Map body = response.getBody();
    
            // Extraer el valor de "currentDateTime", ej. "2025-03-17T16:50Z"
            String currentDateTime = (String) body.get("currentDateTime");
            if (currentDateTime == null || currentDateTime.length() < 10) {
                throw new RuntimeException("La respuesta no contiene currentDateTime válido.");
            }
            // Parsear los primeros 10 caracteres ("YYYY-MM-DD") a LocalDate
            LocalDate fecha = LocalDate.parse(currentDateTime.substring(0, 10));
            return fecha;
        } catch (Exception e) {
            // Si ocurre cualquier error, usamos el fallback a LocalDate.now()
            return LocalDate.now();
        }
    }
}
