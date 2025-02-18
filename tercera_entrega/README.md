
# Proyecto Biblioteca (Java + JPA + Hibernate + H2)

Este proyecto consiste en un sistema básico de Biblioteca desarrollado en **Java** usando **JPA** (con **Hibernate** como proveedor) y **H2** como base de datos embebida (en archivo). 
Permite gestionar libros, autores, editoriales, socios y préstamos.

---

## 1. Requerimientos

- **Java 8+**  
- **Maven 3+**  
- **Visual Studio Code** con:
  - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)  
  - [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)

---

## 2. Estructura del Proyecto

```plaintext
tercera_entrega
│
├── pom.xml
├── README.md
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── coderhouse
│   │   │           └── biblioteca
│   │   │               ├── entidad
│   │   │               │   ├── Autor.java
│   │   │               │   ├── Editorial.java
│   │   │               │   ├── Libro.java
│   │   │               │   ├── Prestamo.java
│   │   │               │   └── Socio.java
│   │   │               └── main
│   │   │                   └── Principal.java
│   │   └── resources
│   │       └── META-INF
│   │           └── persistence.xml
│   └── test
│       └── java
│           └── ...
│
└── target
    └── ...
```

---

## 3. Descripción de Clases

- **Editorial**  
  Representa la editorial que publica uno o varios libros.

- **Autor**  
  Representa un autor de uno o varios libros. Posee una relación **ManyToMany** con `Libro`.

- **Libro**  
  Cada libro pertenece a una editorial (**ManyToOne**) y puede tener varios autores (**ManyToMany**).

- **Socio**  
  Representa a un usuario de la biblioteca que puede retirar libros.

- **Préstamo**  
  Relaciona un socio con un libro, indicando fechas de préstamo y devolución.

---

## 4. Configuración de la Base de Datos (H2)

En el archivo `persistence.xml`, se establece:
```xml
<property name="javax.persistence.jdbc.driver"   value="org.h2.Driver"/>
<property name="javax.persistence.jdbc.url"      value="jdbc:h2:file:./db/biblioteca;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE"/>
<property name="javax.persistence.jdbc.user"     value="sa"/>
<property name="javax.persistence.jdbc.password" value=""/>
<property name="hibernate.dialect"               value="org.hibernate.dialect.H2Dialect"/>
<property name="hibernate.hbm2ddl.auto"          value="update"/>
<property name="hibernate.show_sql"              value="true"/>
<property name="hibernate.format_sql"            value="true"/>
```
Esto hará que, al ejecutar la aplicación, se cree (o actualice) un archivo de base de datos en la ruta `./db/biblioteca.mv.db`.

Para visualizar y editar la base de datos H2:

1. Descarga [H2 Database Engine](https://h2database.com/html/download.html).
2. Ejecuta el jar:
   ```bash
   java -jar h2-<version>.jar
   ```
3. Abre la consola web (normalmente en `http://localhost:8082`).
4. Usa la configuración:
   - **JDBC URL**: `jdbc:h2:file:./db/biblioteca`
   - **User**: `sa`
   - **Password**: *(vacío)*

---

## 5. Ejecución del Proyecto

### 5.1. Compilar y Ejecutar desde la Terminal

1. Asegúrate de tener **Maven** instalado (ejecuta `mvn -version`).
2. Desde la raíz del proyecto (donde está el `pom.xml`), compila con:
   ```bash
   mvn clean compile
   ```
3. Ejecuta la clase principal `Principal` con:
   ```bash
   mvn exec:java '-Dexec.mainClass=com.coderhouse.biblioteca.main.Principal'
   ```

### 5.2. Ejecución en Visual Studio Code

1. Instala el [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
2. Abre la carpeta del proyecto (`tercera_entrega`) en VS Code.
3. Ubica el archivo `Principal.java`.
4. Haz clic en la opción de “Run” o “Debug” que aparece encima de la función `main`.
5. Revisa la consola en la zona de “Terminal” o “Debug Console”.

---

## 6. Ejemplo de Uso

La clase `Principal` tiene un ejemplo básico donde:
1. Crea una `Editorial` y la persiste.
2. Crea un `Autor` y lo persiste.
3. Crea un `Libro`, lo asocia con la editorial y con el autor, y lo persiste.
4. Crea un `Socio` y lo persiste.
5. Genera un `Prestamo` relacionando al socio y al libro, persiste la información.
6. Imprime un mensaje de confirmación.
