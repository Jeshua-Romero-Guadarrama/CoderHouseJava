# Cliente API Rest - Romero

Este proyecto consiste en una API REST en Java (utilizando Spring Boot) que expone información sobre clientes.  
El objetivo principal es:

- **Crear** la tabla `cliente` con los atributos: `nombre`, `apellido`, `fecha_nacimiento`.  
- **Exponer** un endpoint para obtener datos de un cliente en formato JSON, con el siguiente contenido:
  ```json
  {
    "nombre": "XXXXXX",
    "apellido": "YYYYYY",
    "años": "#####"
  }
  ```
- **Calcular** la edad del cliente en la capa de servicio (`ClienteService`).

## 1. Características principales

- **Spring Boot 3.0.0**: facilita la configuración y el arranque de la aplicación.
- **H2 en memoria**: la base de datos se carga al inicio y se destruye al cerrar la aplicación.
- **API REST**: un `RestController` expone el endpoint para consultar el cliente por ID.
- **Cálculo de la edad**: se realiza en la capa de servicio usando `java.time.Period`.

## 2. Requisitos del sistema

- **Java 17** (o la versión que uses, definida en `<java.version>` del `pom.xml`).
- **Maven** (para compilar y empacar el proyecto).
- Un editor de texto o IDE, por ejemplo: Visual Studio Code, IntelliJ o Eclipse.

## 3. Estructura del proyecto

El proyecto sigue la convención estándar de Maven:

```
ClienteApiRestRomero
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── ejemplo
        │           ├── ClienteApiRestRomeroApplication.java
        │           ├── controller
        │           │   └── ClienteController.java
        │           ├── model
        │           │   └── Cliente.java
        │           ├── repository
        │           │   └── ClienteRepository.java
        │           └── service
        │               └── ClienteService.java
        └── resources
            ├── application.properties
            └── data.sql
```

- **`ClienteApiRestRomeroApplication.java`**: Clase principal con el método `main`, donde arranca Spring Boot.  
- **`controller/ClienteController.java`**: Contiene el endpoint REST para consultar un cliente por su ID.  
- **`service/ClienteService.java`**: Lógica de negocio, aquí se calcula la edad de un cliente basándose en su fecha de nacimiento.  
- **`model/Cliente.java`**: Entidad JPA que representa la tabla `cliente`.  
- **`repository/ClienteRepository.java`**: Interfaz que extiende `JpaRepository` para operaciones CRUD.  
- **`application.properties`**: Configuración de la base de datos H2, el puerto del servidor, etc.  
- **`data.sql`**: Script SQL que inserta datos de ejemplo en la tabla `cliente`.

## 4. Cómo instalar y correr el proyecto localmente

1. **Clonar o descargar** este repositorio en tu máquina local.
2. **Abrir** la carpeta del proyecto en tu IDE o editor de preferencia (por ejemplo, VS Code).
3. **Compilar y empacar** en un `.jar` ejecutable:
   ```bash
   mvn clean package
   ```
   Esto generará el archivo `ClienteApiRestRomero-1.0.0.jar` dentro de la carpeta `target/`.
4. **Ejecutar** la aplicación:
   ```bash
   java -jar target/ClienteApiRestRomero-1.0.0.jar
   ```
5. Spring Boot arrancará en el **puerto 8081** (configurado en `application.properties`):
   ```
   http://localhost:8081
   ```

> **Nota**: Si deseas cambiar el puerto, edita la línea `server.port=8081` en el archivo `application.properties`.

## 5. Hacer pruebas (curl, Postman, navegador)

### 5.1. Probar con `curl`

En Windows PowerShell (o cualquier terminal), usar:
```powershell
curl.exe -X GET http://localhost:8081/api/clientes/1
```
> O si quieres consultar otro cliente (ej. id=2, id=3), cambia el número al final de la URL.

Si todo funciona, recibirás un JSON como:
```json
{
  "nombre": "Juan",
  "apellido": "Gomez",
  "años": 34
}
```

### 5.2. Probar con Postman o Thunder Client

1. **Abrir Postman** (o extensión Thunder Client en VS Code).
2. Configurar una **petición GET** a:
   ```
   http://localhost:8081/api/clientes/1
   ```
3. Presionar **Send**.  
4. Verificar la respuesta JSON.

### 5.3. Otros clientes

- **Navegador web**: Puedes abrir `http://localhost:8081/api/clientes/1` en tu navegador. Verás la respuesta JSON (o posiblemente se descargue el archivo, dependiendo del navegador).

## 6. Uso de la consola H2

Si quieres ver la base de datos en memoria:

1. Abre `http://localhost:8081/h2-console`
2. Usa los siguientes datos:
   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **User**: `sa`
   - **Password**: *(vacío)*
3. Haz clic en “Connect”.

Podrás consultar la tabla `cliente` y ver los registros insertados.