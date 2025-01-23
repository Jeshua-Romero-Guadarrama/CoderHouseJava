package com.coderhouse.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Socio de la biblioteca.
 * Un socio puede tener múltiples préstamos registrados en el sistema.
 */
@Entity
@Table(name = "socios")
@Schema(description = "Entidad que representa un socio de la biblioteca, incluyendo su información personal y su historial de préstamos.")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del socio", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // Nombre del socio
    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre completo del socio", example = "Juan Pérez", required = true)
    private String nombre;

    /**
     * Relación OneToMany con la entidad Prestamo.
     * Un socio puede tener muchos préstamos (registrados en diferentes momentos).
     * orphanRemoval = true elimina automáticamente los préstamos asociados si se elimina el socio.
     */
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "Lista de préstamos asociados al socio", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Prestamo> prestamos = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Socio() {
    }

    /**
     * Constructor que inicializa un socio con su nombre.
     *
     * @param nombre El nombre del socio.
     */
    public Socio(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del socio.
     *
     * @return El ID del socio.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del socio.
     *
     * @return El nombre del socio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del socio.
     *
     * @param nombre El nombre que se asignará al socio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de préstamos asociados al socio.
     *
     * @return La lista de préstamos.
     */
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**
     * Asigna la lista de préstamos al socio.
     *
     * @param prestamos La lista de préstamos a asignar.
     */
    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
