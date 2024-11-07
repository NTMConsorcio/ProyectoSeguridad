package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class ContactoTelefonicoDAORest extends BaseDAORestImpl<ContactoTelefonicoDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/contactoTelefonico";
    }
}
