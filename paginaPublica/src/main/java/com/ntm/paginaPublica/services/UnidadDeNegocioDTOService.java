package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.rest.UnidadDeNegocioDAORest;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadDeNegocioDTOService {
    @Autowired
    private UnidadDeNegocioDAORest dao;

    public List<UnidadDeNegocioDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(UnidadDeNegocioDTO[].class);
        } catch (ErrorDAOException ex) {
            throw new ErrorServiceException("Error al listar unidades de negocio");
        }
    }
}
