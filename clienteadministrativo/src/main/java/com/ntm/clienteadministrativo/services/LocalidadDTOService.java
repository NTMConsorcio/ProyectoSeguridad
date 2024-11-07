package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.DepartamentoDTO;
import com.ntm.clienteadministrativo.dto.LocalidadDTO;
import com.ntm.clienteadministrativo.rest.LocalidadDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadDTOService {
    @Autowired
    LocalidadDAORest dao;

    @Autowired
    DepartamentoDTOService service;

    public void crear(String nombre, String idDepartamento) throws ErrorServiceException {

        try {
            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setNombre(nombre);
            localidad.setEliminado(false);
            DepartamentoDTO departamento = service.buscar(idDepartamento);
            localidad.setDepartamento(departamento);
            dao.crear(LocalidadDTO.class, localidad);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String nombre, String idDepartamento) throws ErrorServiceException {

        try {

            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setId(id);
            localidad.setNombre(nombre);
            localidad.setEliminado(false);
            DepartamentoDTO departamento = service.buscar(idDepartamento);
            localidad.setDepartamento(departamento);
            dao.actualizar(localidad);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public LocalidadDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            LocalidadDTO obj = dao.buscar(LocalidadDTO.class, id);

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

    public List<LocalidadDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(LocalidadDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
