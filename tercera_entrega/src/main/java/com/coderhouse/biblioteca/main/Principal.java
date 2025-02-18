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
 * Se encarga de crear un EntityManager, transaccionar y persistir algunas entidades.
 */
public class Principal {

    public static void main(String[] args) {
        // Crear un EntityManagerFactory basado en el persistence-unit "BibliotecaPU"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        // Obtener un EntityManager para iniciar la persistencia
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // 1. Crear una editorial y persistirla
            Editorial editorial1 = new Editorial("Editorial Ejemplo");
            em.persist(editorial1);

            // 2. Crear un autor y persistirlo
            Autor autor1 = new Autor("Gabriel García Márquez", "Colombiana");
            em.persist(autor1);

            // 3. Crear un libro, asociarlo a la editorial y al autor
            Libro libro1 = new Libro("Cien Años de Soledad");
            libro1.setEditorial(editorial1);
            libro1.getAutores().add(autor1);
            em.persist(libro1);

            // (Opcional) Mantener la relación bidireccional
            autor1.getLibros().add(libro1);

            // 4. Crear un socio y persistirlo
            Socio socio1 = new Socio("Juan Pérez");
            em.persist(socio1);

            // 5. Crear un préstamo asociando el socio y el libro
            Prestamo prestamo1 = new Prestamo(
                    socio1,
                    libro1,
                    LocalDate.now(),   // Fecha de préstamo = hoy
                    null               // Fecha de devolución (null = no devuelto)
            );
            em.persist(prestamo1);

            // Confirmar la transacción
            em.getTransaction().commit();

            System.out.println("Datos guardados exitosamente en la base de datos H2.");
        } catch (Exception e) {
            // En caso de error, realizar rollback de la transacción
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
