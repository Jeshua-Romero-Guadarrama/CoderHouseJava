CREATE TABLE IF NOT EXISTS editoriales (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS autores (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS libros (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    editorial_id BIGINT NOT NULL,
    CONSTRAINT fk_libros_editorial FOREIGN KEY (editorial_id) REFERENCES editoriales(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS libros_autores (
    libro_id BIGINT,
    autor_id BIGINT,
    PRIMARY KEY (libro_id, autor_id),
    CONSTRAINT fk_libros_autores_libro FOREIGN KEY (libro_id) REFERENCES libros(id) ON DELETE CASCADE,
    CONSTRAINT fk_libros_autores_autor FOREIGN KEY (autor_id) REFERENCES autores(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS socios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS prestamos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    socio_id BIGINT NOT NULL,
    libro_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    CONSTRAINT fk_prestamos_socio FOREIGN KEY (socio_id) REFERENCES socios(id) ON DELETE CASCADE,
    CONSTRAINT fk_prestamos_libro FOREIGN KEY (libro_id) REFERENCES libros(id) ON DELETE CASCADE
);
