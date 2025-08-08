package sv.edu.udb.hexagonal.domain.ports.in;

import java.util.List;
import java.util.Optional;

import sv.edu.udb.hexagonal.domain.model.Libro;

public interface LibroServicePort {
    Libro crearLibro(Libro libro);

    Optional<Libro> obtenerLibroPorId(Long id);

    List<Libro> obtenerTodosLosLibros();

    List<Libro> obtenerLibrosPorCategoria(Long categoriaId);

    Libro actualizarLibro(Libro libro);

    void eliminarLibro(Long id);
}