package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.ContactoTelefonico;
import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ContactoTelefonicoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;


@Service
public class ContactoTelefonicoServiceImpl extends BaseServiceImpl<ContactoTelefonico,String> implements ContactoTelefonicoService {

    private final ContactoTelefonicoRepository contactoTelefonicoRepository;

    public ContactoTelefonicoServiceImpl(BaseRepository<ContactoTelefonico, String> baserepository, ContactoTelefonicoRepository contactoTelefonicoRepository) {super(baserepository);
        this.contactoTelefonicoRepository = contactoTelefonicoRepository;
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
