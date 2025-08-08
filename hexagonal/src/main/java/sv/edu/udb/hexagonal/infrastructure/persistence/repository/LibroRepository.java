package sv.edu.udb.hexagonal.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.LibroEntity;

import java.util.List;

public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findByCategoriaId(Long categoriaId);
}
