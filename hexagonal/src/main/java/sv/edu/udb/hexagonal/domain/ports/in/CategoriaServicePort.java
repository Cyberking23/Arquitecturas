package sv.edu.udb.hexagonal.domain.ports.in;

import java.util.List;
import java.util.Optional;

import sv.edu.udb.hexagonal.domain.model.Categoria;

public interface CategoriaServicePort {
    Categoria crearCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    List<Categoria> obtenerTodasLasCategorias();

    Categoria actualizarCategoria(Categoria categoria);

    void eliminarCategoria(Long id);
}