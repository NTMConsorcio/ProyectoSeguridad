package com.ntm.appseguridad.services;

import com.ntm.appseguridad.dto.ContactoCorreoElectronicoDTO;
import com.ntm.appseguridad.mappers.ContactoCorreoElectronicoMapper;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ContactoCorreoElectronicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactoCorreoElectronicoServiceImpl extends BaseServiceImpl<ContactoCorreoElectronico,String> implements ContactoCorreoElectronicoService {

    private final ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository;
    private final ContactoCorreoElectronicoMapper contactoCorreoElectronicoMapper;

    public ContactoCorreoElectronicoServiceImpl(BaseRepository<ContactoCorreoElectronico, String> baserepository, ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository, ContactoCorreoElectronicoMapper contactoCorreoElectronicoMapper) {super(baserepository);
        this.contactoCorreoElectronicoRepository = contactoCorreoElectronicoRepository;
        this.contactoCorreoElectronicoMapper = contactoCorreoElectronicoMapper;
    }

    @Override
    public <D> D convertToDto(ContactoCorreoElectronico entity) {
        return (D) contactoCorreoElectronicoMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<ContactoCorreoElectronico> entities) {
        return (List<D>) contactoCorreoElectronicoMapper.toDtoList(entities);
    }

    public ContactoCorreoElectronicoDTO Crear(ContactoCorreoElectronicoDTO contactoDto) {
        ContactoCorreoElectronico contacto = contactoCorreoElectronicoMapper.toEntity(contactoDto);
        ContactoCorreoElectronico contactoGuardado = repository.save(contacto);
        return contactoCorreoElectronicoMapper.toDTO(contactoGuardado);
    }

    @Override
    public boolean validar(ContactoCorreoElectronico entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
                throw new ErrorServiceException("Debe indicar un email");
            }

            if (caso.equals("SAVE")) {
                if (contactoCorreoElectronicoRepository.existsByEmailAndEliminadoFalse(entity.getEmail())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                ContactoCorreoElectronico cc = contactoCorreoElectronicoRepository.findByEmailAndEliminadoFalse(entity.getEmail());
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
