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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ContactoTelefonicoMapper.class, ContactoCorreoElectronicoMapper.class})
public interface ContactoMapper {
<<<<<<< HEAD

    ContactoMapper INSTANCE = Mappers.getMapper(ContactoMapper.class);
=======
    ContactoDTO toDTO(Contacto contacto);
>>>>>>> d4a99a2d142086d11d0cdb39f85fece958c0a474

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

    default List<Contacto> toEntityList(List<ContactoDTO> contactoDTOs, ContactoTelefonicoMapper contactoTelefonicoMapper,
                                        ContactoCorreoElectronicoMapper contactoCorreoElectronicoMapper) {
        List<Contacto> contactos = new ArrayList<>();
        for (ContactoDTO dto : contactoDTOs) {
            Contacto contacto = null;

            if (dto instanceof ContactoTelefonicoDTO) {
                contacto = contactoTelefonicoMapper.toEntity((ContactoTelefonicoDTO) dto);
            } else if (dto instanceof ContactoCorreoElectronicoDTO) {
                contacto = contactoCorreoElectronicoMapper.toEntity((ContactoCorreoElectronicoDTO) dto);
            }

            if (contacto != null) {
                contactos.add(contacto);
            }
        }
        return contactos;
    }
}

