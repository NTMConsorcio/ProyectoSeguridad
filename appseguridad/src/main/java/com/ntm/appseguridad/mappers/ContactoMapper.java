package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.ContactoCorreoElectronicoDTO;
import com.ntm.appseguridad.dto.ContactoDTO;
import com.ntm.appseguridad.dto.ContactoTelefonicoDTO;
import com.ntm.appseguridad.entities.Contacto;
import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import com.ntm.appseguridad.entities.ContactoTelefonico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ContactoTelefonicoMapper.class, ContactoCorreoElectronicoMapper.class})
public interface ContactoMapper {

    ContactoMapper INSTANCE = Mappers.getMapper(ContactoMapper.class);

    @SubclassMapping(source = ContactoTelefonico.class, target = ContactoTelefonicoDTO.class)
    @SubclassMapping(source = ContactoCorreoElectronico.class, target = ContactoCorreoElectronicoDTO.class)
    ContactoDTO toDTO(Contacto contacto);

    @SubclassMapping(source = ContactoTelefonicoDTO.class, target = ContactoTelefonico.class)
    @SubclassMapping(source = ContactoCorreoElectronicoDTO.class, target = ContactoCorreoElectronico.class)
    Contacto toEntity(ContactoDTO contactoDTO);

    List<ContactoDTO> toDTOList(List<Contacto> contactos);

    List<Contacto> toEntityList(List<ContactoDTO> contactoDTOs);

}

