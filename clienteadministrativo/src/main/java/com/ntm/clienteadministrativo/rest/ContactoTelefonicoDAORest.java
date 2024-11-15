package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class ContactoTelefonicoDAORest extends BaseDAORestImpl<ContactoTelefonicoDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://appseguridad:9000/api/v1/contactoTelefonico";
    }

    public void crearC(ContactoTelefonicoDTO contacto) throws ErrorServiceException {
        try {
            String uri = "http://appseguridad:9000/api/v1/contactoTelefonico/crear";
            restTemplate.postForEntity(uri, contacto, ContactoTelefonicoDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
