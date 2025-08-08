package sv.edu.udb.hexagonal.domain.ports.out;

import sv.edu.udb.hexagonal.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaPersistencePort {
    Categoria guardarCategoria(Categoria categoria);

    Optional<Categoria> findById(Long id);

    List<Categoria> findAll();

    void deleteById(Long id);
}
