package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class PaisDAORest extends BaseDAORestImpl<PaisDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://appseguridad:9000/api/v1/pais";
    }
}
