package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.InmuebleDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;

public class InmuebleDAORest extends BaseDAORestImpl<InmuebleDTO,String>{
    @Override
    public String getUri(String caso) throws ErrorServiceException{
        return "http://localhost:9000/api/v1/inmueble";
    }
}
