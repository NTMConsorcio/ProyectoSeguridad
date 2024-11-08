package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;

public class UnidadDeNegocioDAORest extends BaseDAORestImpl<UnidadDeNegocioDTO, String>{
    @Override
    public String getUri(String caso) throws ErrorServiceException{
        return "http://localhost:9000/api/v1/unidadDeNegocio";
    }
}
