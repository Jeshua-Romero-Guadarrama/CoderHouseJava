package com.coderhouse.biblioteca.dto;

import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.media.Schema;

import com.coderhouse.biblioteca.model.Autor;
import com.coderhouse.biblioteca.model.Editorial;
import com.coderhouse.biblioteca.model.Libro;
import com.coderhouse.biblioteca.model.Prestamo;
import com.coderhouse.biblioteca.model.Socio;

/**
 * Clase de utilidades que mapea Entidad ↔ DTO.
 * <p>
 * Esta clase proporciona métodos estáticos para convertir entre las entidades del dominio
 * y sus correspondientes objetos de transferencia de datos (DTO). Esto facilita la separación
 * de la lógica de negocio de la presentación y permite adaptar la información para las API.
 * </p>
 */
@Schema(description = "Utilidad para mapear entidades a DTO y viceversa. Provee métodos para convertir objetos de dominio a sus representaciones DTO y retornar los datos adecuados para la capa de presentación.")
public class Mapper {

    /**
     * Convierte un objeto {@link Autor} a su representación en {@link AutorDTO}.
     *
     * @param autor Objeto de dominio Autor a convertir.
     * @return Un objeto {@link AutorDTO} equivalente, o null si el autor es null.
     */
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

    /**
     * Convierte un objeto {@link AutorDTO} a su entidad de dominio {@link Autor}.
     *
     * @param dto Objeto AutorDTO a convertir.
     * @return Un objeto {@link Autor} creado a partir del DTO, o null si el DTO es null.
     */
    public static Autor dtoToAutor(AutorDTO dto) {
        if (dto == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        autor.setNacionalidad(dto.getNacionalidad());
        return autor;
    }

    /**
     * Convierte un objeto {@link Editorial} a su representación en {@link EditorialDTO}.
     *
     * @param editorial Objeto Editorial a convertir.
     * @return Un objeto {@link EditorialDTO} equivalente, o null si la editorial es null.
     */
    public static EditorialDTO editorialToDTO(Editorial editorial) {
        if (editorial == null) {
            return null;
        }
        EditorialDTO dto = new EditorialDTO();
        dto.setId(editorial.getId());
        dto.setNombre(editorial.getNombre());
        return dto;
    }

    /**
     * Convierte un objeto {@link EditorialDTO} a su entidad de dominio {@link Editorial}.
     *
     * @param dto Objeto EditorialDTO a convertir.
     * @return Un objeto {@link Editorial} creado a partir del DTO, o null si el DTO es null.
     */
    public static Editorial dtoToEditorial(EditorialDTO dto) {
        if (dto == null) {
            return null;
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(dto.getNombre());
        return editorial;
    }

    /**
     * Convierte un objeto {@link Libro} a su representación en {@link LibroDTO}.
     *
     * @param libro Objeto Libro a convertir.
     * @return Un objeto {@link LibroDTO} equivalente, o null si el libro es null.
     */
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

    /**
     * Convierte un objeto {@link LibroDTO} a su entidad de dominio {@link Libro}.
     *
     * @param dto Objeto LibroDTO a convertir.
     * @return Un objeto {@link Libro} creado a partir del DTO, o null si el DTO es null.
     */
    public static Libro dtoToLibro(LibroDTO dto) {
        if (dto == null) {
            return null;
        }
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        // Si el stock enviado es 0 o menor, asignar 1 por defecto.
        if (dto.getStock() <= 0) {
            libro.setStock(1);
        } else {
            libro.setStock(dto.getStock());
        }
        return libro;
    }

    /**
     * Convierte un objeto {@link Socio} a su representación en {@link SocioDTO}.
     *
     * @param socio Objeto Socio a convertir.
     * @return Un objeto {@link SocioDTO} equivalente, o null si el socio es null.
     */
    public static SocioDTO socioToDTO(Socio socio) {
        if (socio == null) {
            return null;
        }
        SocioDTO dto = new SocioDTO();
        dto.setId(socio.getId());
        dto.setNombre(socio.getNombre());
        return dto;
    }

    /**
     * Convierte un objeto {@link SocioDTO} a su entidad de dominio {@link Socio}.
     *
     * @param dto Objeto SocioDTO a convertir.
     * @return Un objeto {@link Socio} creado a partir del DTO, o null si el DTO es null.
     */
    public static Socio dtoToSocio(SocioDTO dto) {
        if (dto == null) {
            return null;
        }
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        return socio;
    }

    /**
     * Convierte un objeto {@link Prestamo} a su representación en {@link PrestamoDTO}.
     *
     * @param prestamo Objeto Prestamo a convertir.
     * @return Un objeto {@link PrestamoDTO} equivalente, o null si el préstamo es null.
     */
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

    /**
     * Convierte un objeto {@link PrestamoDTO} a su entidad de dominio {@link Prestamo}.
     *
     * @param dto Objeto PrestamoDTO a convertir.
     * @return Un objeto {@link Prestamo} creado a partir del DTO, o null si el DTO es null.
     */
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
