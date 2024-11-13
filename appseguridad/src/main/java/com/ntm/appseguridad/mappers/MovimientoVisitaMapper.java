package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.MovimientoVisitaDTO;
import com.ntm.appseguridad.entities.MovimientoVisita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InmuebleMapper.class, VisitanteMapper.class})
public interface MovimientoVisitaMapper {
    MovimientoVisitaMapper INSTANCE = Mappers.getMapper(MovimientoVisitaMapper.class);

    @Mapping(source = "inmueble", target = "inmueble")
    @Mapping(source = "visitante", target = "visitante")
    MovimientoVisitaDTO toDTO(MovimientoVisita movimientoVisita);

    @Mapping(source = "inmueble", target = "inmueble")
    @Mapping(source = "visitante", target = "visitante")
    MovimientoVisita toEntity(MovimientoVisitaDTO movimientoVisitaDTO);

    List<MovimientoVisitaDTO> toDtoList(List<MovimientoVisita> movimientoVisitas);
}
