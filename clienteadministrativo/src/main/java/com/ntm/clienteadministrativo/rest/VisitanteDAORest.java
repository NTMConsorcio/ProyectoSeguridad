package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.VisitanteDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;

public class VisitanteDAORest extends BaseDAORestImpl<VisitanteDTO,String> {
    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/visitante";
    }
}
