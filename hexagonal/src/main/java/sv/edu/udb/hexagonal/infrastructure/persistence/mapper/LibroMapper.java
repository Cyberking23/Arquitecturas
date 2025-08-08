package sv.edu.udb.hexagonal.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.udb.hexagonal.domain.model.Libro;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.LibroEntity;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    @Mapping(source = "categoria", target = "categoria")
    LibroEntity toEntity(Libro libro);

    @Mapping(source = "categoria", target = "categoria")
    Libro toDomain(LibroEntity entity);
}
