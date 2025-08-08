package sv.edu.udb.hexagonal.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import sv.edu.udb.hexagonal.domain.model.Libro;
import sv.edu.udb.hexagonal.domain.ports.out.LibroPersistencePort;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.LibroEntity;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.CategoriaEntity;
import sv.edu.udb.hexagonal.infrastructure.persistence.mapper.LibroMapper;
import sv.edu.udb.hexagonal.infrastructure.persistence.mapper.CategoriaMapper;
import sv.edu.udb.hexagonal.infrastructure.persistence.repository.LibroRepository;
import sv.edu.udb.hexagonal.infrastructure.persistence.repository.CategoriaRepository;

@Component
public class LibroPersistenceAdapter implements LibroPersistencePort {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;
    private final LibroMapper libroMapper;
    private final CategoriaMapper categoriaMapper;

    public LibroPersistenceAdapter(LibroRepository libroRepository,
                                   CategoriaRepository categoriaRepository,
                                   LibroMapper libroMapper,
                                   CategoriaMapper categoriaMapper) {
        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
        this.libroMapper = libroMapper;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        LibroEntity entity = libroMapper.toEntity(libro);

        CategoriaEntity categoria = categoriaRepository.findById(libro.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + libro.getCategoria().getId()));
        entity.setCategoria(categoria);
        LibroEntity savedEntity = libroRepository.save(entity);
        return libroMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id)
                .map(libroMapper::toDomain);
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll().stream()
                .map(libroMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> findByCategoriaId(Long categoriaId) {
        return libroRepository.findByCategoriaId(categoriaId).stream()
                .map(libroMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }
}
