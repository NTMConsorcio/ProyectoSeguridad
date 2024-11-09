package com.ntm.appseguridad.services;


import com.ntm.appseguridad.dto.ContactoTelefonicoDTO;
import com.ntm.appseguridad.entities.ContactoTelefonico;
import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.mappers.ContactoTelefonicoMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ContactoTelefonicoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactoTelefonicoServiceImpl extends BaseServiceImpl<ContactoTelefonico,String> implements ContactoTelefonicoService {

    private final ContactoTelefonicoRepository contactoTelefonicoRepository;
    private final ContactoTelefonicoMapper contactoTelefonicoMapper;

    public ContactoTelefonicoServiceImpl(BaseRepository<ContactoTelefonico, String> baserepository, ContactoTelefonicoRepository contactoTelefonicoRepository, ContactoTelefonicoMapper contactoTelefonicoMapper) {super(baserepository);
        this.contactoTelefonicoRepository = contactoTelefonicoRepository;
        this.contactoTelefonicoMapper = contactoTelefonicoMapper;
    }

    public ContactoTelefonicoDTO Crear(ContactoTelefonicoDTO contactoDto) {
        ContactoTelefonico contacto = contactoTelefonicoMapper.toEntity(contactoDto);
        ContactoTelefonico contactoGuardado = repository.save(contacto);
        return contactoTelefonicoMapper.toDTO(contactoGuardado);
    }
    @Override
    public <D> D convertToDto(ContactoTelefonico entity) {
        return null;
    }

    @Override
    public <D> List<D> convertToDtoList(List<ContactoTelefonico> entities) {
        return (List<D>) contactoTelefonicoMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(ContactoTelefonico entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getTelefono() == null || entity.getTelefono().isEmpty()) {
                throw new ErrorServiceException("Debe indicar un teléfono");
            }

            if (caso.equals("SAVE")) {
                if (contactoTelefonicoRepository.existsByTelefonoAndEliminadoFalse(entity.getTelefono())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                ContactoTelefonico cc = contactoTelefonicoRepository.findByTelefonoAndEliminadoFalse(entity.getTelefono());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El objeto especificado ya existe en el sistema");
                    }
                }
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }

    }
}
