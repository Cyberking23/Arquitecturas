package sv.edu.udb.hexagonal.domain.ports.out;

import java.util.List;
import java.util.Optional;

import sv.edu.udb.hexagonal.domain.model.Libro;

public interface LibroPersistencePort {
    Libro guardarLibro(Libro libro);

    Optional<Libro> findById(Long id);

    List<Libro> findAll();

    List<Libro> findByCategoriaId(Long categoriaId);

    void deleteById(Long id);
}
