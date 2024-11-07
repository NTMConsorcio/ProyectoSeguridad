package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.LocalidadDTO;
import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.rest.DireccionDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionDTOService {
    @Autowired
    DireccionDAORest dao;

    @Autowired
    LocalidadDTOService service;

    public void crear(String calle, String numeracion, String latitud, String longitud, String idLocalidad) throws ErrorServiceException {

        try {
            DireccionDTO direccion = new DireccionDTO();
            direccion.setCalle(calle);
            direccion.setNumeracion(numeracion);
            direccion.setLatitud(latitud);
            direccion.setLongitud(longitud);
            direccion.setEliminado(false);

            LocalidadDTO localidad = service.buscar(idLocalidad);
            direccion.setLocalidad(localidad);
            dao.crear(DireccionDTO.class, direccion);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String calle, String numeracion, String latitud, String longitud, String idLocalidad) throws ErrorServiceException {

        try {

            DireccionDTO direccion = new DireccionDTO();
            direccion.setId(id);
            direccion.setCalle(calle);
            direccion.setNumeracion(numeracion);
            direccion.setLatitud(latitud);
            direccion.setLongitud(longitud);
            direccion.setEliminado(false);

            LocalidadDTO localidad = service.buscar(idLocalidad);
            direccion.setLocalidad(localidad);
            dao.actualizar(direccion);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DireccionDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            DireccionDTO obj = dao.buscar(DireccionDTO.class, id);

            return obj;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            dao.eliminar(id);

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    public List<DireccionDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(DireccionDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
