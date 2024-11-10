package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.EmpleadoDTO;
import com.ntm.appseguridad.entities.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {UnidadDeNegocioMapper.class, UsuarioMapper.class, ContactoMapper.class})
public interface EmpleadoMapper {

    EmpleadoMapper INSTANCE = Mappers.getMapper(EmpleadoMapper.class);

    // Mapeo: Convertir Empleado a EmpleadoDTO
    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    @Mapping(source = "contactos", target = "contactos")
    EmpleadoDTO toDTO(Empleado empleado);

    // Mapeo inverso: Convertir EmpleadoDTO a Empleado
    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    @Mapping(source = "contactos", target = "contactos")
    Empleado toEntity(EmpleadoDTO empleadoDTO);

    List<EmpleadoDTO> toDtoList(List<Empleado> entities);

}
