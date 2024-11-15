package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class DireccionDAORest extends BaseDAORestImpl<DireccionDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/direccion";
    }
}
