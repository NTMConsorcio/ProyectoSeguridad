package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.dto.ServicioDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.VisitanteDTO;
import com.ntm.clienteadministrativo.rest.UnidadDeNegocioDAORest;
import com.ntm.clienteadministrativo.rest.VisitanteDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitanteDTOService {

    @Autowired
    VisitanteDAORest dao;


    public void crear(String nombre, String apellido, int documento) throws ErrorServiceException {
        try {
            VisitanteDTO visitante = new VisitanteDTO(nombre, apellido, documento);
            visitante.setEliminado(false);
            dao.crear(VisitanteDTO.class,visitante);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    public void modificar(String id, String nombre, String apellido, int documento) throws ErrorServiceException {

        try {
            VisitanteDTO visitante = new VisitanteDTO(nombre, apellido, documento);
            visitante.setId(id);
            visitante.setEliminado(false);
            dao.actualizar(visitante);


        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
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

    public List<VisitanteDTO> getActivos() throws ErrorServiceException{
        try {
            List<VisitanteDTO> visitantes = dao.getActivos();
            return visitantes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public VisitanteDTO buscar(String id) throws ErrorServiceException{
        try{
            VisitanteDTO visitanteDTO = dao.buscar(VisitanteDTO.class,id);
            return visitanteDTO;
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
