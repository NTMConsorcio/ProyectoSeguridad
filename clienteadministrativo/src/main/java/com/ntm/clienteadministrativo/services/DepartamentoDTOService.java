package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.ProvinciaDTO;
import com.ntm.clienteadministrativo.dto.DepartamentoDTO;
import com.ntm.clienteadministrativo.rest.DepartamentoDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoDTOService {
    @Autowired
    DepartamentoDAORest dao;

    @Autowired
    ProvinciaDTOService service;

    public void crear(String nombre, String idProvincia) throws ErrorServiceException {

        try {
            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setNombre(nombre);
            departamento.setEliminado(false);
            ProvinciaDTO provincia = service.buscar(idProvincia);
            departamento.setProvincia(provincia);
            dao.crear(DepartamentoDTO.class, departamento);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String nombre, String idProvincia) throws ErrorServiceException {

        try {

            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setId(id);
            departamento.setNombre(nombre);
            departamento.setEliminado(false);

            ProvinciaDTO provincia = service.buscar(idProvincia);
            departamento.setProvincia(provincia);
            dao.actualizar(departamento);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public DepartamentoDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            DepartamentoDTO obj = dao.buscar(DepartamentoDTO.class, id);

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

    public List<DepartamentoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(DepartamentoDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
