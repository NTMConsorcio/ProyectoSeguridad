package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.EmpresaDTO;
import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaDAORest extends BaseDAORestImpl<EmpresaDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/empresa";
    }
}
