package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.rest.ServicioDAORest;
import com.ntm.paginaPublica.rest.UnidadDeNegocioDAORest;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDTOService {
    @Autowired
    private ServicioDAORest dao;

    public List<ServicioDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ServicioDTO[].class);
        } catch (ErrorDAOException ex) {
            throw new ErrorServiceException("Error al listar servicios");
        }
    }
}
