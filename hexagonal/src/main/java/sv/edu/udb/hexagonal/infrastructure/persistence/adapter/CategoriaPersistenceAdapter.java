package sv.edu.udb.hexagonal.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sv.edu.udb.hexagonal.domain.model.Categoria;
import sv.edu.udb.hexagonal.domain.ports.out.CategoriaPersistencePort;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.CategoriaEntity;
import sv.edu.udb.hexagonal.infrastructure.persistence.mapper.CategoriaMapper;
import sv.edu.udb.hexagonal.infrastructure.persistence.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoriaPersistenceAdapter implements CategoriaPersistencePort {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        CategoriaEntity entity = categoriaMapper.toEntity(categoria);
        return categoriaMapper.toDomain(categoriaRepository.save(entity));
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDomain);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}