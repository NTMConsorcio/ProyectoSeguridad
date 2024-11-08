package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.DepartamentoDTO;
import com.ntm.appseguridad.entities.Departamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProvinciaMapper.class)
public interface DepartamentoMapper {
    DepartamentoMapper INSTANCE = Mappers.getMapper(DepartamentoMapper.class);

    @Mapping(source = "provincia", target = "provincia")
    DepartamentoDTO toDTO(Departamento departamento);

    @Mapping(source = "provincia", target = "provincia")
    Departamento toEntity(DepartamentoDTO departamentoDTO);

    List<DepartamentoDTO> toDtoList(List<Departamento> entities);
}
