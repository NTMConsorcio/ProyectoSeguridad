package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.PaisDTO;
import com.ntm.appseguridad.entities.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaisMapper {
    PaisMapper INSTANCE = Mappers.getMapper(PaisMapper.class);

    PaisDTO toDTO(Pais pais);

    Pais toEntity(PaisDTO paisDTO);

    List<PaisDTO> toDtoList(List<Pais> entities);
}
