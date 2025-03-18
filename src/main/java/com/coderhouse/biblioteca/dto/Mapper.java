package com.coderhouse.biblioteca.dto;

import java.util.stream.Collectors;

import com.coderhouse.biblioteca.model.Autor;
import com.coderhouse.biblioteca.model.Editorial;
import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.model.Prestamo;
import com.coderhouse.biblioteca.model.Socio;

/**
 * Clase de utilidades que mapea Entidad ↔ DTO.
 */
public class Mapper {

    // Convertir Autor -> AutorDTO
    public static AutorDTO autorToDTO(Autor autor) {
        if (autor == null) {
            return null;
        }
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());
        dto.setNacionalidad(autor.getNacionalidad());
        return dto;
    }

    // Convertir AutorDTO -> Autor
    public static Autor dtoToAutor(AutorDTO dto) {
        if (dto == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        autor.setNacionalidad(dto.getNacionalidad());
        return autor;
    }

    // Editorial -> EditorialDTO
    public static EditorialDTO editorialToDTO(Editorial editorial) {
        if (editorial == null) {
            return null;
        }
        EditorialDTO dto = new EditorialDTO();
        dto.setId(editorial.getId());
        dto.setNombre(editorial.getNombre());
        return dto;
    }

    // EditorialDTO -> Editorial
    public static Editorial dtoToEditorial(EditorialDTO dto) {
        if (dto == null) {
            return null;
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(dto.getNombre());
        return editorial;
    }

    // Libro -> LibroDTO
    public static LibroDTO libroToDTO(Libro libro) {
        if (libro == null) {
            return null;
        }
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setEditorialId(libro.getEditorial() != null ? libro.getEditorial().getId() : null);
        dto.setAutoresIds(
                libro.getAutores()
                        .stream()
                        .map(Autor::getId)
                        .collect(Collectors.toList())
        );
        dto.setStock(libro.getStock());
        return dto;
    }

    // LibroDTO -> Libro
    public static Libro dtoToLibro(LibroDTO dto) {
        if (dto == null) {
            return null;
        }
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        // Si el stock enviado es 0 o menor, asignar 1 por defecto
        if (dto.getStock() <= 0) {
            libro.setStock(1);
        } else {
            libro.setStock(dto.getStock());
        }
        return libro;
    }

    // Socio -> SocioDTO
    public static SocioDTO socioToDTO(Socio socio) {
        if (socio == null) {
            return null;
        }
        SocioDTO dto = new SocioDTO();
        dto.setId(socio.getId());
        dto.setNombre(socio.getNombre());
        return dto;
    }

    // SocioDTO -> Socio
    public static Socio dtoToSocio(SocioDTO dto) {
        if (dto == null) {
            return null;
        }
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        return socio;
    }

    // Prestamo -> PrestamoDTO
    public static PrestamoDTO prestamoToDTO(Prestamo prestamo) {
        if (prestamo == null) {
            return null;
        }
        PrestamoDTO dto = new PrestamoDTO();
        dto.setId(prestamo.getId());
        dto.setSocioId(prestamo.getSocio().getId());
        dto.setLibroId(prestamo.getLibro().getId());
        dto.setFechaPrestamo(prestamo.getFechaPrestamo());
        dto.setFechaDevolucion(prestamo.getFechaDevolucion());
        return dto;
    }

    // PrestamoDTO -> Prestamo
    public static Prestamo dtoToPrestamo(PrestamoDTO dto) {
        if (dto == null) {
            return null;
        }
        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        return prestamo;
    }
}
