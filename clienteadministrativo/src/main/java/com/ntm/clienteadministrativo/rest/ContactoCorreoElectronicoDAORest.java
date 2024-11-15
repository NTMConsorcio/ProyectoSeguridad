package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.ContactoCorreoElectronicoDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class ContactoCorreoElectronicoDAORest extends BaseDAORestImpl<ContactoCorreoElectronicoDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://appseguridad:9000/api/v1/contactoCorreoElectronico";
    }

    public void crearC(ContactoCorreoElectronicoDTO contacto) throws ErrorServiceException {
        try {
            String uri = "http://appseguridad:9000/api/v1/contactoCorreoElectronico/crear";
            restTemplate.postForEntity(uri, contacto, ContactoCorreoElectronicoDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
