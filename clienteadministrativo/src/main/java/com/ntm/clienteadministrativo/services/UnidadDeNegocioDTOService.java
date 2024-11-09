package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.rest.UnidadDeNegocioDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadDeNegocioDTOService {

    @Autowired
    UnidadDeNegocioDAORest unidadDao;

    public List<UnidadDeNegocioDTO> getActivos() throws ErrorServiceException{
        try {
            List<UnidadDeNegocioDTO> unidades = unidadDao.getActivos();
            return unidades;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
