package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ContactoCorreoElectronicoDTO;
import com.ntm.appseguridad.dto.ContactoDTO;
import com.ntm.appseguridad.dto.ContactoTelefonicoDTO;
import com.ntm.appseguridad.entities.Contacto;
import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import com.ntm.appseguridad.entities.ContactoTelefonico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ContactoTelefonicoMapper.class, ContactoCorreoElectronicoMapper.class})
public interface ContactoMapper {

    ContactoDTO toDTO(Contacto contacto);

    Contacto toEntity(ContactoDTO contactoDTO);

    List<ContactoDTO> toDtoList(List<Contacto> entities);

    List<Contacto> toList(List<ContactoDTO> entities);
}