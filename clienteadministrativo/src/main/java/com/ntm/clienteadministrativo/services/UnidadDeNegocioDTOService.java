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

    public UnidadDeNegocioDTO buscar(String id) throws ErrorServiceException{
        try {
            // Intentar buscar la unidad de negocio usando el DAO
            UnidadDeNegocioDTO unidad = unidadDao.buscar(id);
            if (unidad == null) {
                // Si la unidad de negocio no se encuentra, lanzar una excepción con un mensaje más claro
                throw new ErrorServiceException("Unidad de negocio no encontrada para el ID: " + id);
            }
            return unidad;
        } catch (ErrorServiceException ex) {
            // Manejar una excepción específica
            throw new ErrorServiceException("Error al buscar la unidad de negocio: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar excepciones generales
            throw new ErrorServiceException("Error de sistemas. Por favor, intente más tarde.");
        }
    }
}
