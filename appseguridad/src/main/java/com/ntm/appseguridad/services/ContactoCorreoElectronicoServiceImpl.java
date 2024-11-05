package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ContactoCorreoElectronicoRepository;
import org.springframework.stereotype.Service;


@Service
public class ContactoCorreoElectronicoServiceImpl extends BaseServiceImpl<ContactoCorreoElectronico,String> implements ContactoCorreoElectronicoService {

    private final ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository;

    public ContactoCorreoElectronicoServiceImpl(BaseRepository<ContactoCorreoElectronico, String> baserepository, ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository) {super(baserepository);
        this.contactoCorreoElectronicoRepository = contactoCorreoElectronicoRepository;
    }

    public boolean validar(ContactoCorreoElectronico entity) {
        return true;
    }
}
