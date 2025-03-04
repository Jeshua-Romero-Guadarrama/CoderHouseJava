# Proyecto Biblioteca (Spring Boot + JPA + Hibernate + H2)

Este proyecto consiste en un sistema básico de Biblioteca desarrollado en **Java** usando **Spring Boot**, **JPA** (con **Hibernate** como proveedor) y **H2** como base de datos embebida.

## 1. Requerimientos

- **Java 21+**
- **Maven 3+**
- **Visual Studio Code** (o cualquier otro IDE como IntelliJ o Eclipse) con:
  - Extension Pack for Java
  - Maven for Java

## 2. Estructura del Proyecto

```plaintext
quinta_entrega
│
├── pom.xml
├── README.md
├── scripts
│   ├── schema.sql
│   └── data.sql
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── coderhouse
│   │   │           └── biblioteca
│   │   │               ├── SpringBootBibliotecaApplication.java
│   │   │               ├── controller
│   │   │               │   ├── AutorController.java
│   │   │               │   ├── EditorialController.java
│   │   │               │   ├── LibroController.java
│   │   │               │   ├── PrestamoController.java
│   │   │               │   └── SocioController.java
│   │   │               ├── model
│   │   │               │   ├── Autor.java
│   │   │               │   ├── Editorial.java
│   │   │               │   ├── Libro.java
│   │   │               │   ├── Prestamo.java
│   │   │               │   └── Socio.java
│   │   │               ├── repository
│   │   │               │   ├── AutorRepository.java
│   │   │               │   ├── EditorialRepository.java
│   │   │               │   ├── LibroRepository.java
│   │   │               │   ├── PrestamoRepository.java
│   │   │               │   └── SocioRepository.java
│   │   │               └── service
│   │   │                   ├── AutorService.java
│   │   │                   ├── EditorialService.java
│   │   │                   ├── LibroService.java
│   │   │                   ├── PrestamoService.java
│   │   │                   └── SocioService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── META-INF
│   │           └── persistence.xml   (No es obligatorio en Spring Boot, pero se puede incluir)
│   └── test
│       └── java
│           └── ...
```

## 3. Ejecución del Proyecto

### 3.1. Ejecución con Maven (línea de comandos)

1. Abre una terminal y navega hasta la carpeta raíz del proyecto (donde está el `pom.xml`).
2. Compila el proyecto:
   ```bash
   mvn clean install
   ```
3. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
4. Verás en consola: `Aplicación Spring Boot de Biblioteca iniciada exitosamente.`

### 3.2. Ejecución en Visual Studio Code

1. Abre la carpeta del proyecto en VS Code.
2. Instala las extensiones recomendadas para Java (Extension Pack for Java).
3. En la barra lateral, ubica la clase `SpringBootBibliotecaApplication.java` y busca el botón "Run" o "Debug".
4. El proyecto iniciará y podrás ver logs en la ventana de Output o Terminal.

## 4. Uso de la API REST

El proyecto expone endpoints REST, por ejemplo (corre en `http://localhost:9090`):

- **Autores**  
  - `GET /api/autores`  
  - `GET /api/autores/{id}`  
  - `POST /api/autores`  
  - `PUT /api/autores/{id}`  
  - `DELETE /api/autores/{id}`  
- **Editoriales**  
  - `GET /api/editoriales`  
  - etc...
- **Libros**  
  - `GET /api/libros`  
  - etc...
- **Prestamos**  
  - `GET /api/prestamos`  
  - etc...
- **Socios**  
  - `GET /api/socios`  
  - etc...

## 5. Acceso a la Consola H2

1. Por defecto, la aplicación expone la consola en:  
   `http://localhost:9090/h2-console`
2. En la pantalla de login:
   - JDBC URL: `jdbc:h2:mem:biblioteca`
   - User Name: `sa`
   - Password: *(vacío)*

## 6. Pruebas de la API desde la Terminal

Se pueden utilizar herramientas de línea de comandos como **curl** para enviar peticiones HTTP. 

A continuación, se muestran 10 ejemplos de pruebas que cubren distintos endpoints y métodos:

### Ejemplo 1: Consultar todas las editoriales

```bash
curl.exe -X GET "http://localhost:9090/api/editoriales"
```

### Ejemplo 2: Consultar todos los autores

```bash
curl.exe -X GET "http://localhost:9090/api/autores"
```

### Ejemplo 3: Consultar todos los libros

```bash
curl.exe -X GET "http://localhost:9090/api/libros"
```

### Ejemplo 4: Consultar todos los préstamos

```bash
curl.exe -X GET "http://localhost:9090/api/prestamos"
```

### Ejemplo 5: Consultar una Editorial por ID

```bash
curl.exe -X GET http://localhost:9090/api/editoriales/1
```

### Ejemplo 6: Crear una Editorial

```bash
curl.exe -X POST "http://localhost:9090/api/editoriales" -H "Content-Type: application/json" -d "{\"nombre\": \"Nueva Editorial desde POST\"}"
```

### Ejemplo 7: Crear un Autor

```bash
curl.exe -X POST "http://localhost:9090/api/autores" -H "Content-Type: application/json" -d "{\"nombre\": \"Autor Creado por POST\", \"nacionalidad\": \"Argentina\"}"
```

### Ejemplo 8: Crear un Libro

```bash
curl.exe -X POST "http://localhost:9090/api/libros" -H "Content-Type: application/json" -d "{\"titulo\": \"Libro Nuevo\", \"editorial\": {\"id\": 1}, \"autores\": [{\"id\": 1}, {\"id\": 2}]}"
```

### Ejemplo 9: Crear un Socio

```bash
curl.exe -X POST "http://localhost:9090/api/socios" -H "Content-Type: application/json" -d "{\"nombre\": \"Socio Nuevo\"}"
```

### Ejemplo 14: Crear un Préstamo

```bash
curl.exe -X POST "http://localhost:9090/api/prestamos" -H "Content-Type: application/json" -d "{\"socio\": {\"id\": 2}, \"libro\": {\"id\": 5}, \"fechaPrestamo\": \"2025-03-01\", \"fechaDevolucion\": null}"
```

### Ejemplo 10: Actualizar una Editorial

```bash
curl.exe -X PUT "http://localhost:9090/api/editoriales/1" -H "Content-Type: application/json" -d "{\"nombre\": \"Editorial Actualizada\"}"
```

### Ejemplo 11: Actualizar un Autor

```bash
curl.exe -X PUT "http://localhost:9090/api/autores/1" -H "Content-Type: application/json" -d "{\"nombre\": \"Autor Modificado\", \"nacionalidad\": \"Chile\"}"
```

### Ejemplo 12: Actualizar un Libro

```bash
curl.exe -X PUT "http://localhost:9090/api/libros/1" -H "Content-Type: application/json" -d "{\"titulo\": \"Título Modificado\", \"editorial\": {\"id\": 2}, \"autores\": [{\"id\": 3}]}"
```

### Ejemplo 13: Actualizar un Socio

```bash
curl.exe -X PUT "http://localhost:9090/api/socios/1" -H "Content-Type: application/json" -d "{\"nombre\": \"Socio Editado\"}"
```

### Ejemplo 15: Actualizar un Préstamo

```bash
curl.exe -X POST "http://localhost:9090/api/prestamos" -H "Content-Type: application/json" -d "{\"socio\": {\"id\": 2}, \"libro\": {\"id\": 5}, \"fechaPrestamo\": \"2025-03-01\", \"fechaDevolucion\": null}"
```

### Ejemplo 16: Eliminar una Editorial

```bash
curl.exe -X DELETE "http://localhost:9090/api/editoriales/1"
```

### Ejemplo 17: Eliminar un Autor

```bash
curl.exe -X DELETE "http://localhost:9090/api/autores/1"
```

### Ejemplo 18: Eliminar un Libro

```bash
curl.exe -X DELETE "http://localhost:9090/api/libros/1"
```

### Ejemplo 19: Eliminar un Socio

```bash
curl.exe -X DELETE "http://localhost:9090/api/socios/1"
```

### Ejemplo 20: Eliminar un Préstamo

```bash
curl.exe -X DELETE "http://localhost:9090/api/prestamos/1"
```