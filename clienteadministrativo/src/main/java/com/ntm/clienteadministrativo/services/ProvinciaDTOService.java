package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.dto.ProvinciaDTO;
import com.ntm.clienteadministrativo.rest.ProvinciaDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaDTOService {
    @Autowired
    ProvinciaDAORest dao;

    @Autowired
    PaisDTOService service;

    public void crear(String nombre, String idPais) throws ErrorServiceException {

        try {
            ProvinciaDTO provincia = new ProvinciaDTO();
            provincia.setNombre(nombre);
            provincia.setEliminado(false);
            PaisDTO pais = service.buscar(idPais);
            provincia.setPais(pais);
            dao.crear(ProvinciaDTO.class, provincia);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String nombre, String idPais) throws ErrorServiceException {

        try {

            ProvinciaDTO provincia = new ProvinciaDTO();
            provincia.setId(id);
            provincia.setNombre(nombre);
            provincia.setEliminado(false);
            PaisDTO pais = service.buscar(idPais);
            provincia.setPais(pais);
            dao.actualizar(provincia);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ProvinciaDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            ProvinciaDTO obj = dao.buscar(ProvinciaDTO.class, id);

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

    public List<ProvinciaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ProvinciaDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
