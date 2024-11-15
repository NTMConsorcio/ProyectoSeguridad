package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.dto.EmpresaDTO;
import com.ntm.paginaPublica.rest.EmpresaDAORest;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaDTOService {
    @Autowired
    private EmpresaDAORest dao;

    public List<EmpresaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(EmpresaDTO[].class);
        } catch (Exception ex) {
            throw new ErrorServiceException(ex.getMessage());
        }
    }
}
