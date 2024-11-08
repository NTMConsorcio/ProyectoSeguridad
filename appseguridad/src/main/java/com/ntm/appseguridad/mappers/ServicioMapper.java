package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ServicioDTO;
import com.ntm.appseguridad.entities.Servicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImagenMapper.class, EmpresaMapper.class})
public interface ServicioMapper {
    ServicioMapper INSTANCE = Mappers.getMapper(ServicioMapper.class);

    @Mapping(source = "imagen", target = "imagen")
    @Mapping(source = "empresa", target = "empresa")
    ServicioDTO toDTO(Servicio servicio);

    @Mapping(source = "imagen", target = "imagen")
    @Mapping(source = "empresa", target = "empresa")
    Servicio toEntity(ServicioDTO servicioDTO);

    List<ServicioDTO> toDtoList(List<Servicio> entities);
}

