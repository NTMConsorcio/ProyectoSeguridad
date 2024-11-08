package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.EmpleadoDTO;
import com.ntm.appseguridad.entities.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = UnidadDeNegocioMapper.class)
public interface EmpleadoMapper {
    EmpleadoMapper INSTANCE = Mappers.getMapper(EmpleadoMapper.class);

    //Mapeo: Convertir UnidadDeNegocio a UnidadDeServicioDTO
    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    EmpleadoDTO toEmpleadoDTO(Empleado empleado);

    //Mapeo inverso: Convertir UnidadDeServicioDTO a UnidadDeNegocio
    @Mapping(source = "unidadDeNegocio", target = "unidadDeNegocio")
    Empleado toEmpleado(EmpleadoDTO empleadoDTO);
}
