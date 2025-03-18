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
sexta_entrega
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
│   │   │               ├── dto
│   │   │               │   ├── AutorDTO.java
│   │   │               │   ├── EditorialDTO.java
│   │   │               │   ├── LibroDTO.java
│   │   │               │   ├── PrestamoDTO.java
│   │   │               │   ├── SocioDTO.java
│   │   │               │   └── Mapper.java
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
│   │       ├── schema.sql
│   │       └── data.sql
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

## 6. Pruebas de la API desde la Terminal (GIT BASH)

Se pueden utilizar herramientas de línea de comandos como **curl** para enviar peticiones HTTP. 

A continuación, se muestran 10 ejemplos de pruebas que cubren distintos endpoints y métodos:

### Ejemplo 1: Crear una Editorial

```bash
curl -X POST "http://localhost:9090/api/editoriales" -H "Content-Type: application/json" -d '{"nombre":"Nueva Editorial desde POST"}'
```

### Ejemplo 2: Crear un Autor

```bash
curl -X POST "http://localhost:9090/api/autores" -H "Content-Type: application/json" -d '{"nombre":"Autor Creado por POST","nacionalidad":"Argentina"}'
```

### Ejemplo 3: Crear un Libro

```bash
curl -X POST "http://localhost:9090/api/libros" -H "Content-Type: application/json" -d '{"titulo":"Libro Nuevo","editorialId":1,"autoresIds":[1,2]}'
```

### Ejemplo 4: Crear un Socio

```bash
curl -X POST "http://localhost:9090/api/socios" -H "Content-Type: application/json" -d '{"nombre":"Socio Nuevo"}'
```

### Ejemplo 5: Crear un Préstamo

```bash
curl -X POST "http://localhost:9090/api/prestamos" -H "Content-Type: application/json" -d '{"socioId":1,"libroId":1,"fechaPrestamo":"2025-03-01","fechaDevolucion":null}'
```

### Ejemplo 6: Consultar todas las editoriales

```bash
curl -X GET "http://localhost:9090/api/editoriales"
```

### Ejemplo 7: Consultar todos los autores

```bash
curl -X GET "http://localhost:9090/api/autores"
```

### Ejemplo 8: Consultar todos los libros

```bash
curl -X GET "http://localhost:9090/api/libros"
```

### Ejemplo 9: Consultar todos los socios

```bash
curl -X GET "http://localhost:9090/api/socios"
```

### Ejemplo 10: Consultar todos los préstamos

```bash
curl -X GET "http://localhost:9090/api/prestamos"
```

### Ejemplo 11: Consultar una Editorial por ID

```bash
curl -X GET "http://localhost:9090/api/editoriales/1"
```

### Ejemplo 12: Actualizar una Editorial

```bash
curl -X PUT "http://localhost:9090/api/editoriales/1" -H "Content-Type: application/json" -d '{"nombre":"Editorial Actualizada"}'
```

### Ejemplo 13: Actualizar un Autor

```bash
curl -X PUT "http://localhost:9090/api/autores/1" -H "Content-Type: application/json" -d '{"nombre":"Autor Modificado","nacionalidad":"Chile"}'
```

### Ejemplo 14: Actualizar un Libro

```bash
curl -X PUT "http://localhost:9090/api/libros/1" -H "Content-Type: application/json" -d '{"titulo":"Título Modificado","editorialId":1,"autoresIds":[1,2]}'
```

### Ejemplo 15: Actualizar un Socio

```bash
curl -X PUT "http://localhost:9090/api/socios/1" -H "Content-Type: application/json" -d '{"nombre":"Socio Editado"}'
```

### Ejemplo 16: Actualizar un Préstamo

```bash
curl -X PUT "http://localhost:9090/api/prestamos/1" -H "Content-Type: application/json" -d '{"socioId":2,"libroId":1,"fechaPrestamo":"2025-03-01","fechaDevolucion":null}'
```

### Ejemplo 17: Eliminar una Editorial

```bash
curl -X DELETE "http://localhost:9090/api/editoriales/1"
```

### Ejemplo 18: Eliminar un Autor

```bash
curl -X DELETE "http://localhost:9090/api/autores/1"
```

### Ejemplo 19: Eliminar un Libro

```bash
curl -X DELETE "http://localhost:9090/api/libros/1"
```

### Ejemplo 20: Eliminar un Socio

```bash
curl -X DELETE "http://localhost:9090/api/socios/1"
```

### Ejemplo 21: Eliminar un Préstamo

```bash
curl -X DELETE "http://localhost:9090/api/prestamos/1"
```

### Ejemplo 22: Crear un Comprobante de Préstamo Múltiple

- El objeto `socio` debe tener el campo `id` (un socio existente).  
- Cada elemento en `lineas` indica cuántas unidades (`cantidad`) y cuál libro se presta (`\"libro\": { \"id\": X }`).  
- Internamente, el sistema validará:  
  1. Que el socio exista.  
  2. Que los libros existan.  
  3. Que haya suficiente **stock** de cada libro. Si no hay stock suficiente, se lanza excepción.  
  4. Se descuenta del stock la cantidad prestada.  
  5. Se toma la fecha desde un servicio externo o se hace fallback a `LocalDate.now()`.  
  6. Retorna el comprobante creado con su `id`, la `fechaComprobante`, el `totalLibros`, etc.  

```bash
# 1. Crear una Editorial
curl -s -X POST "http://localhost:9090/api/editoriales" -H "Content-Type: application/json" \
  -d '{"nombre": "Editorial de Prueba"}'
echo -e "\nEditorial creada"

# 2. Crear un Autor
curl -s -X POST "http://localhost:9090/api/autores" -H "Content-Type: application/json" \
  -d '{"nombre": "Autor de Prueba", "nacionalidad": "Prueba"}'
echo -e "\nAutor creado"

# 3. Crear varios Libros
for i in {1..10}
do
  curl -s -X POST "http://localhost:9090/api/libros" -H "Content-Type: application/json" \
    -d "{\"titulo\": \"Libro Nuevo $i\", \"editorialId\": 1, \"autoresIds\": [1], \"stock\": 2}"
  echo -e "\nLibro $i creado"
done

# 4. Crear un Socio
curl -s -X POST "http://localhost:9090/api/socios" -H "Content-Type: application/json" \
  -d '{"nombre": "Socio de Prueba"}'
echo -e "\nSocio creado"

# 5. Crear el Comprobante de Préstamo
curl -X POST "http://localhost:9090/api/comprobantes" \
  -H "Content-Type: application/json" \
  -d '{"socio":{"id":1},"lineas":[{"cantidad":1,"libro":{"id":1}},{"cantidad":1,"libro":{"id":2}}]}'
echo -e "\nComprobante creado"
```

### Ejemplo 23: Crear un Comprobante de Préstamo Múltiple con Datos Inválidos

- Se envía un `socio` que no existe.
 
```bash
curl -X POST "http://localhost:9090/api/comprobantes" \
  -H "Content-Type: application/json" \
  -d '{"socio":{"id":999},"lineas":[{"cantidad":1,"libro":{"id":1}}]}'
```

- Se solicita `cantidad` mayor al `stock`. 

```bash
curl -X POST "http://localhost:9090/api/comprobantes" \
  -H "Content-Type: application/json" \
  -d '{"socio":{"id":1},"lineas":[{"cantidad":5,"libro":{"id":3}}]}'
```