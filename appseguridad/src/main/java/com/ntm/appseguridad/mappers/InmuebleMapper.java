package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.DepartamentoDTO;
import com.ntm.appseguridad.dto.InmuebleDTO;
import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.entities.Inmueble;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UnidadDeNegocioMapper.class)
public interface InmuebleMapper {
    InmuebleMapper INSTANCE = Mappers.getMapper(InmuebleMapper.class);

    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    InmuebleDTO toDTO(Inmueble inmueble);

    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    Inmueble toEntity(InmuebleDTO inmuebleDTO);

    List<InmuebleDTO> toDtoList(List<Inmueble> entities);
}

