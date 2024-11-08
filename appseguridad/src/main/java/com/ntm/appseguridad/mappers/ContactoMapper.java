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

@Mapper(componentModel = "spring")
public interface ContactoMapper {
    ContactoMapper INSTANCE = Mappers.getMapper(ContactoMapper.class);

    ContactoTelefonicoDTO toDTO(ContactoTelefonico contacto);

    ContactoTelefonico toEntity(ContactoTelefonicoDTO contactoDTO);

    ContactoCorreoElectronicoDTO toDTO(ContactoCorreoElectronico contacto);

    ContactoCorreoElectronico toEntity(ContactoCorreoElectronicoDTO contactoDTO);

    default ContactoDTO toDTO(Contacto contacto) {
        if (contacto instanceof ContactoTelefonico) {
            return toDTO((ContactoTelefonico) contacto);
        } else if (contacto instanceof ContactoCorreoElectronico) {
            return toDTO((ContactoCorreoElectronico) contacto);
        }
        return null;
    }

    default Contacto toEntity(ContactoDTO contactoDTO) {
        if (contactoDTO instanceof ContactoTelefonicoDTO) {
            return toEntity((ContactoTelefonicoDTO) contactoDTO);
        } else if (contactoDTO instanceof ContactoCorreoElectronicoDTO) {
            return toEntity((ContactoCorreoElectronicoDTO) contactoDTO);
        }
        return null;
    }
}

