package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.VisitanteDTO;
import com.ntm.appseguridad.entities.Visitante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitanteMapper {
    VisitanteMapper INSTANCE = Mappers.getMapper(VisitanteMapper.class);

    VisitanteDTO toDto(Visitante visitante);

    Visitante toVisitante(VisitanteDTO visitanteDTO);

    List<VisitanteDTO> toDtoList(List<Visitante> visitantes);

}
