package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.MovimientoVisitaDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;

public class MovimientoVisitaDAORest extends BaseDAORestImpl<MovimientoVisitaDTO,String> {
    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/movimientoVisita";
    }
}
