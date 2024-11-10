package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ContactoCorreoElectronicoDTO;
import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactoCorreoElectronicoMapper {
    ContactoCorreoElectronicoMapper INSTANCE = Mappers.getMapper(ContactoCorreoElectronicoMapper.class);
    @Mapping(target = "type", expression = "java(\"correo\")")
    ContactoCorreoElectronicoDTO toDTO(ContactoCorreoElectronico contactoCorreoElectronico);

    ContactoCorreoElectronico toEntity(ContactoCorreoElectronicoDTO contactoCorreoElectronicoDTO);

    List<ContactoCorreoElectronicoDTO> toDtoList(List<ContactoCorreoElectronico> entities);
}
