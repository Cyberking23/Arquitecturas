package sv.edu.udb.hexagonal.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Categoria categoria;
}
