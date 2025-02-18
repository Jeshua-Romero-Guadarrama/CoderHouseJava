package com.coderhouse.biblioteca.main;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.coderhouse.biblioteca.entidad.Autor;
import com.coderhouse.biblioteca.entidad.Editorial;
import com.coderhouse.biblioteca.entidad.Libro;
import com.coderhouse.biblioteca.entidad.Prestamo;
import com.coderhouse.biblioteca.entidad.Socio;

/**
 * Clase principal de la aplicación de Biblioteca.
 */
public class Principal {

    public static void main(String[] args) {
        // Crear un EntityManagerFactory basado en el persistence-unit "BibliotecaPU"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar transacción
            em.getTransaction().begin();

            // Crear entidades de ejemplo
            Editorial editorial1 = new Editorial("Editorial Ejemplo");
            em.persist(editorial1);

            Autor autor1 = new Autor("Gabriel García Márquez", "Colombiana");
            em.persist(autor1);

            Libro libro1 = new Libro("Cien Años de Soledad");
            libro1.setEditorial(editorial1);
            libro1.getAutores().add(autor1);
            em.persist(libro1);

            // El autor también referencia al libro (opcional si quieres guardar la bidireccionalidad)
            autor1.getLibros().add(libro1);

            Socio socio1 = new Socio("Juan Pérez");
            em.persist(socio1);

            // Crear un préstamo
            Prestamo prestamo1 = new Prestamo(
                    socio1,
                    libro1,
                    LocalDate.now(),        // fecha de préstamo
                    null                    // fecha de devolución (null significa que aún no devolvió)
            );
            em.persist(prestamo1);

            // Confirmar la transacción
            em.getTransaction().commit();

            System.out.println("Datos guardados exitosamente en la base de datos H2.");

        } catch (Exception e) {
            // En caso de error, hacemos rollback
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar EntityManager y EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}