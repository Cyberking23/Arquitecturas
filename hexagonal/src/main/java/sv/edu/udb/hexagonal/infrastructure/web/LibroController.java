package sv.edu.udb.hexagonal.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.web.server.ResponseStatusException;
import sv.edu.udb.hexagonal.application.service.LibroServiceImpl;
import sv.edu.udb.hexagonal.domain.model.Libro;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroServiceImpl libroService;

    public LibroController(LibroServiceImpl libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        // Asegurar que no se envíe ID manual
        libro.setId(null);

        // Validar que la categoría exista
        if (libro.getCategoria() == null || libro.getCategoria().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Debe especificar una categoría válida"
            );
        }

        Libro nuevoLibro = libroService.crearLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
        Optional<Libro> libro = libroService.obtenerLibroPorId(id);
        return libro.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return new ResponseEntity<>(libroService.obtenerTodosLosLibros(), HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Libro>> librosPorCategoria(@PathVariable Long categoriaId) {
        return new ResponseEntity<>(libroService.obtenerLibrosPorCategoria(categoriaId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        libro.setId(id);
        return new ResponseEntity<>(libroService.actualizarLibro(libro), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
