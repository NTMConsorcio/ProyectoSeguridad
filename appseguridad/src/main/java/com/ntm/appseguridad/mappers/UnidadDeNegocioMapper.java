package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.UnidadDeNegocioDTO;
import com.ntm.appseguridad.entities.UnidadDeNegocio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadDeNegocioMapper {
    UnidadDeNegocioMapper INSTANCE = Mappers.getMapper(UnidadDeNegocioMapper.class);

    // Convertir UnidadDeNegocio a UnidadDeNegocioDTO
    UnidadDeNegocioDTO toUnidadDeNegocioDTO(UnidadDeNegocio unidadDeNegocio);

    // Convertir UnidadDeNegocioDTO a UnidadDeNegocio
    UnidadDeNegocio toUnidadDeNegocio(UnidadDeNegocioDTO unidadDeNegocioDTO);

    List<UnidadDeNegocioDTO> toDtoList(List<UnidadDeNegocio> unidadDeNegocios);
}
