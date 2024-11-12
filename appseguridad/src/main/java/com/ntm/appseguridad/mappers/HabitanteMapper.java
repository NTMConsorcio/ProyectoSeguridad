package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.HabitanteDTO;
import com.ntm.appseguridad.entities.Habitante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InmuebleMapper.class, UsuarioMapper.class, ContactoMapper.class})
public interface HabitanteMapper {
    HabitanteMapper INSTANCE = Mappers.getMapper(HabitanteMapper.class);

    // Mapeo: Convertir Habitante a HabitanteDTO
    @Mapping(source = "inmueble", target = "inmueble")
    @Mapping(source = "contactos", target = "contactos")
    HabitanteDTO toDTO(Habitante habitante);

    // Mapeo inverso: Convertir HabitanteDTO a Habitante
    @Mapping(source = "inmueble", target = "inmueble")
    @Mapping(source = "contactos", target = "contactos")
    Habitante toEntity(HabitanteDTO habitanteDTO);

    List<HabitanteDTO> toDtoList(List<Habitante> entities);

}
