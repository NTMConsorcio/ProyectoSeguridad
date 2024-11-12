package com.ntm.appseguridad.mappers;


import com.ntm.appseguridad.dto.PlanillaHorariaDTO;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = EmpleadoMapper.class)
public interface PlanillaHorariaMapper {

    PlanillaHorariaMapper INSTANCE = Mappers.getMapper(PlanillaHorariaMapper.class);

    // Mapeo: Convertir PlanillaHoraria a PlanillaHorariaDTO

    @Mapping(source = "empleado", target = "empleado")
    PlanillaHorariaDTO toDTO(PlanillaHoraria planillaHoraria);

    // Mapeo inverso: Convertir PlanillaHorariaDTO a PlanillaHoraria
    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    @Mapping(source = "empleado", target = "empleado")
    PlanillaHoraria toEntity(PlanillaHorariaDTO planillaHorariaDTO);

    List<PlanillaHorariaDTO> toDtoList(List<PlanillaHoraria> entities);

}
