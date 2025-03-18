package com.coderhouse.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot.
 * Al anotarla con @SpringBootApplication, se configuran de forma automática aspectos como escaneo de componentes, configuración de JPA, entre otros.
 */
@SpringBootApplication
public class SpringBootBibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBibliotecaApplication.class, args);
        System.out.println("Aplicación Spring Boot de Biblioteca iniciada exitosamente.");
    }
}
