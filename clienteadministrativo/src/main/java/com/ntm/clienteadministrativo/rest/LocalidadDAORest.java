package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.LocalidadDTO;
import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class LocalidadDAORest extends BaseDAORestImpl<LocalidadDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/localidad";
    }
}
