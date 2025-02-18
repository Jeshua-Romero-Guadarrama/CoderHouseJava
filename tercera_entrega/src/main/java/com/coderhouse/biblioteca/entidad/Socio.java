package com.coderhouse.biblioteca.entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un Socio de la biblioteca (una persona que retira libros).
 */
@Entity
@Table(name = "socios")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Un socio puede tener múltiples préstamos a lo largo del tiempo.
     * Se utiliza orphanRemoval=true para eliminar automáticamente
     * los préstamos asociados si se elimina el socio.
     */
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Socio() {
    }

    public Socio(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
