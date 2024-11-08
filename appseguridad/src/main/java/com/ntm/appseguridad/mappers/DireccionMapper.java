package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.DireccionDTO;
import com.ntm.appseguridad.entities.Direccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocalidadMapper.class)
public interface DireccionMapper {
    DireccionMapper INSTANCE = Mappers.getMapper(DireccionMapper.class);

    @Mapping(source = "localidad", target = "localidad")
    DireccionDTO toDTO(Direccion direccion);

    @Mapping(source = "localidad", target = "localidad")
    Direccion toEntity(DireccionDTO direccionDTO);

    List<DireccionDTO> toDtoList(List<Direccion> entities);
}
