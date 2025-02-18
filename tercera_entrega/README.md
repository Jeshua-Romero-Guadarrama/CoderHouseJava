# Proyecto Biblioteca (Java + JPA/H2)

Este proyecto demuestra un sistema básico de Biblioteca usando **Java** y **JPA** con **Hibernate** como proveedor, y **H2** como base de datos local (en archivo).

## Descripción

- **Editorial**: Una editorial publica varios libros (OneToMany).
- **Libro**: Cada libro pertenece a una editorial y puede tener varios autores (ManyToOne y ManyToMany).
- **Autor**: Un autor puede escribir varios libros (ManyToMany).
- **Socio**: Representa a un usuario de la biblioteca.
- **Préstamo**: Relaciona un socio con un libro prestado (ManyToOne).

## Requisitos

- Java 8+  
- Visual Studio Code con:
  - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)  
  - (Opcional) [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)

## Estructura del proyecto

```
biblioteca

```

## Ejecución en VS Code

1. Clonar o descargar este repositorio.
2. Abrir la carpeta `biblioteca` en VS Code.
3. Verificar que tengas Java y las extensiones de Java instaladas.
4. En la **terminal integrada** (View → Terminal), puedes ejecutar:
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="biblioteca.main.Principal"
   ```
   o bien usar el explorador de proyectos Java y hacer clic en “Run” sobre `Principal.java`.

## Base de datos H2

- Se creará un archivo `biblioteca.mv.db` en la carpeta `db/` (ver configuración en `persistence.xml`).
- Para revisar su contenido, puedes:
  1. Descargar el [jar de H2](https://h2database.com/html/download.html).
  2. Ejecutarlo con `java -jar h2-xxx.jar`.
  3. En la consola web (generalmente `http://localhost:8082`):
     - **JDBC URL**: `jdbc:h2:file:./db/biblioteca`
     - **User**: `sa`
     - **Password**: *(vacío)*
  4. Pulsa “Connect” y podrás ver y editar las tablas.

  