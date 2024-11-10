package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.UnidadDeNegocioDTO;
import com.ntm.appseguridad.entities.UnidadDeNegocio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ServicioMapper.class, DireccionMapper.class})
public interface UnidadDeNegocioMapper {
    UnidadDeNegocioMapper INSTANCE = Mappers.getMapper(UnidadDeNegocioMapper.class);

    // Convertir UnidadDeNegocio a UnidadDeNegocioDTO
    @Mapping(source = "servicio", target = "servicio")
    @Mapping(source = "direccion", target = "direccion")
    UnidadDeNegocioDTO toUnidadDeNegocioDTO(UnidadDeNegocio unidadDeNegocio);

    // Convertir UnidadDeNegocioDTO a UnidadDeNegocio
    @Mapping(source = "servicio", target = "servicio")
    @Mapping(source = "direccion", target = "direccion")
    UnidadDeNegocio toUnidadDeNegocio(UnidadDeNegocioDTO unidadDeNegocioDTO);

    List<UnidadDeNegocioDTO> toDtoList(List<UnidadDeNegocio> unidadDeNegocios);
}
