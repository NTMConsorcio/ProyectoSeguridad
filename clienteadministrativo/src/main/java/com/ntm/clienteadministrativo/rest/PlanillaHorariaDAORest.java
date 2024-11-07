package com.ntm.clienteadministrativo.rest;


import com.ntm.clienteadministrativo.dto.PlanillaHorariaDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;

public class PlanillaHorariaDAORest  extends BaseDAORestImpl<PlanillaHorariaDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/planilla-horaria";
    }
}
