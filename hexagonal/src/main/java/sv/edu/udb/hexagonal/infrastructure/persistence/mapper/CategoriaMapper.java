package sv.edu.udb.hexagonal.infrastructure.persistence.mapper;

import sv.edu.udb.hexagonal.domain.model.Categoria;
import sv.edu.udb.hexagonal.infrastructure.persistence.entity.CategoriaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toDomain(CategoriaEntity entity);

    CategoriaEntity toEntity(Categoria domain);
}