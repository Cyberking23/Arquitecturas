package sv.edu.udb.hexagonal.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.hexagonal.domain.ports.in.CategoriaServicePort;
import sv.edu.udb.hexagonal.domain.ports.out.CategoriaPersistencePort;
import sv.edu.udb.hexagonal.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaServicePort {

    private final CategoriaPersistencePort categoriaPersistencePort;

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaPersistencePort.guardarCategoria(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaPersistencePort.findById(id);
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaPersistencePort.findAll();
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaPersistencePort.guardarCategoria(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaPersistencePort.deleteById(id);
    }
}