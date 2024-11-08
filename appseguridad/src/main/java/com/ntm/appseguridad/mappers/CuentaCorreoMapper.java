package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.CuentaCorreoDTO;
import com.ntm.appseguridad.entities.CuentaCorreo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = CuentaCorreoMapper.class)
public interface CuentaCorreoMapper {
    CuentaCorreoMapper INSTANCE = Mappers.getMapper(CuentaCorreoMapper.class);

    @Mapping(source = "empresa", target = "empresa")
    CuentaCorreoDTO toDTO(CuentaCorreo cuentaCorreo);

    @Mapping(source = "empresa", target = "empresa")
    CuentaCorreo toEntity(CuentaCorreoDTO cuentaCorreoDTO);

    List<CuentaCorreoDTO> toDtoList(List<CuentaCorreo> entities);
}
