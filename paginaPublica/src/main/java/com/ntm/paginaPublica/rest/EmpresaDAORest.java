package com.ntm.paginaPublica.rest;

import com.ntm.paginaPublica.dto.EmpresaDTO;
import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaDAORest extends BaseDAORestImpl<EmpresaDTO>{
    @Override
    public String getUri(String caso) throws ErrorDAOException {
        return "http://appseguridad:9000/api/v1/empresa";
    }
}