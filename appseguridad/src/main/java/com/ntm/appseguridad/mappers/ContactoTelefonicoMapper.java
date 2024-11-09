package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ContactoTelefonicoDTO;
import com.ntm.appseguridad.entities.ContactoTelefonico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactoTelefonicoMapper {
    ContactoTelefonicoMapper INSTANCE = Mappers.getMapper(ContactoTelefonicoMapper.class);

    ContactoTelefonicoDTO toDTO(ContactoTelefonico contactoTelefonico);
    ContactoTelefonico toEntity(ContactoTelefonicoDTO contactoTelefonicoDTO);

    List<ContactoTelefonicoDTO> toDtoList(List<ContactoTelefonico> entities);
}
