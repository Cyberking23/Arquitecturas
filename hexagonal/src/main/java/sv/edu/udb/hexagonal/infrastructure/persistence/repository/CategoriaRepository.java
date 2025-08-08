package sv.edu.udb.hexagonal.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    boolean existsByNombre(String nombre);
}
