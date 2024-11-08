package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ProvinciaDTO;
import com.ntm.appseguridad.entities.Provincia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = PaisMapper.class)
public interface ProvinciaMapper {
    ProvinciaMapper INSTANCE = Mappers.getMapper(ProvinciaMapper.class);

    @Mapping(source = "pais", target = "pais")
    ProvinciaDTO toDTO(Provincia provincia);

    @Mapping(source = "pais", target = "pais")
    Provincia toEntity(ProvinciaDTO provinciaDTO);

    List<ProvinciaDTO> toDtoList(List<Provincia> entities);
}
