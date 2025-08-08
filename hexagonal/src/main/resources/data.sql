-- Borra los datos existentes primero
DELETE FROM libros;
DELETE FROM categorias;

-- Reinicia las secuencias de ID
ALTER TABLE libros ALTER COLUMN id RESTART WITH 1;
ALTER TABLE categorias ALTER COLUMN id RESTART WITH 1;

-- Inserta categorías sin IDs explícitos
INSERT INTO categorias (nombre) VALUES
                                    ('Ficción'),
                                    ('Ciencia'),
                                    ('Fantasía'),
                                    ('Terror');

-- Inserta libros sin IDs explícitos
INSERT INTO libros (titulo, autor, isbn, categoria_id) VALUES
                                                           ('Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', 1),
                                                           ('El Aleph', 'Jorge Luis Borges', '978-0307950907', 1),
                                                           ('Cosmos', 'Carl Sagan', '978-0349107035', 2),
                                                           ('El Señor de los Anillos', 'J.R.R. Tolkien', '978-0544003415', 3),
                                                           ('It', 'Stephen King', '978-1501142970', 4);