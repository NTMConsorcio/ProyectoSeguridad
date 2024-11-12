package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.MovimientoVisitaDTO;
import com.ntm.appseguridad.entities.MovimientoVisita;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoVisitaMapper {
    MovimientoVisitaMapper INSTANCE = Mappers.getMapper(MovimientoVisitaMapper.class);

    MovimientoVisitaDTO toDTO(MovimientoVisita movimientoVisita);

    MovimientoVisita toEntity(MovimientoVisitaDTO movimientoVisitaDTO);

    List<MovimientoVisitaDTO> toDtoList(List<MovimientoVisita> movimientoVisitas);
}
