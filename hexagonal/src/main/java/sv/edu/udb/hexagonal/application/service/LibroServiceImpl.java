package sv.edu.udb.hexagonal.application.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import sv.edu.udb.hexagonal.domain.ports.in.LibroServicePort;
import sv.edu.udb.hexagonal.domain.ports.out.LibroPersistencePort;
import sv.edu.udb.hexagonal.domain.model.Libro;

@Service
public class LibroServiceImpl implements LibroServicePort {

    private final LibroPersistencePort libroPersistencePort;

    public LibroServiceImpl(LibroPersistencePort libroPersistencePort) {
        this.libroPersistencePort = libroPersistencePort;
    }

    @Override
    public Libro crearLibro(Libro libro) {
        return libroPersistencePort.guardarLibro(libro);
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroPersistencePort.findById(id);
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return libroPersistencePort.findAll();
    }

    @Override
    public List<Libro> obtenerLibrosPorCategoria(Long categoriaId) {
        return libroPersistencePort.findByCategoriaId(categoriaId);
    }

    @Override
    public Libro actualizarLibro(Libro libro) {
        return libroPersistencePort.guardarLibro(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroPersistencePort.deleteById(id);
    }
}