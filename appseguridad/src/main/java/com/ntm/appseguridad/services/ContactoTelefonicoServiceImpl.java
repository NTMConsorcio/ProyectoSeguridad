package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.ContactoTelefonico;
import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ContactoTelefonicoRepository;
import org.springframework.stereotype.Service;


@Service
public class ContactoTelefonicoServiceImpl extends BaseServiceImpl<ContactoTelefonico,String> implements ContactoTelefonicoService {

    private final ContactoTelefonicoRepository contactoTelefonicoRepository;

    public ContactoTelefonicoServiceImpl(BaseRepository<ContactoTelefonico, String> baserepository, ContactoTelefonicoRepository contactoTelefonicoRepository) {super(baserepository);
        this.contactoTelefonicoRepository = contactoTelefonicoRepository;
    }

    public boolean validar(ContactoTelefonico entity) {
        return true;
    }
}
