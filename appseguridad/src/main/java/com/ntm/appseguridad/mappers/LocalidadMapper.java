package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.LocalidadDTO;
import com.ntm.appseguridad.entities.Localidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = DepartamentoMapper.class)
public interface LocalidadMapper {
    LocalidadMapper INSTANCE = Mappers.getMapper(LocalidadMapper.class);

    @Mapping(source = "departamento", target = "departamento")
    LocalidadDTO toDTO(Localidad localidad);

    @Mapping(source = "departamento", target = "departamento")
    Localidad toEntity(LocalidadDTO localidadDTO);

    List<LocalidadDTO> toDtoList(List<Localidad> entities);
}