package com.ntm.paginaPublica.rest;

import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import org.springframework.stereotype.Service;

@Service
public class UnidadDeNegocioDAORest extends BaseDAORestImpl<UnidadDeNegocioDTO> {
    @Override
    public String getUri(String caso) throws ErrorDAOException {
        return "http://localhost:9000/api/v1/unidadDeNegocio";
    }
}
