package com.ntm.clienteadministrativo.services;
import com.ntm.clienteadministrativo.dto.InmuebleDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.enums.EstadoInmueble;
import com.ntm.clienteadministrativo.rest.InmuebleDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InmuebleDTOService {

    @Autowired
    InmuebleDAORest dao;

    @Autowired
    UnidadDeNegocioDTOService unidadDeNegocioService;

    public void crear(String numeracion, String piso, String depto, EstadoInmueble estadoInmueble, String idUnidadDeNegocio) throws ErrorServiceException {

        try {
            InmuebleDTO inmuebleDTO = new InmuebleDTO();
            inmuebleDTO.setEstadoInmueble(estadoInmueble);
            inmuebleDTO.setNumeracion(numeracion);
            inmuebleDTO.setPiso(piso);
            inmuebleDTO.setDpto(depto);

            inmuebleDTO.setEliminado(false);
            UnidadDeNegocioDTO unidadDeNegocioDTO = unidadDeNegocioService.buscar(idUnidadDeNegocio);
            inmuebleDTO.setUnidadDeNegocio(unidadDeNegocioDTO);
            dao.crear(InmuebleDTO.class, inmuebleDTO);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String numeracion, String piso, String dpto, EstadoInmueble estadoInmueble, String idUnidadDeNegocio) throws ErrorServiceException {

        try {

            InmuebleDTO inmueble = new InmuebleDTO();
            inmueble.setId(id);
            inmueble.setNumeracion(numeracion);
            inmueble.setDpto(dpto);
            inmueble.setPiso(piso);
            inmueble.setEstadoInmueble(estadoInmueble);
            inmueble.setEliminado(false);

            UnidadDeNegocioDTO unidadDeNegocio = unidadDeNegocioService.buscar(idUnidadDeNegocio);
            inmueble.setUnidadDeNegocio(unidadDeNegocio);
            dao.actualizar(inmueble);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public InmuebleDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            InmuebleDTO obj = dao.buscar(InmuebleDTO.class, id);

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

    public List<InmuebleDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(InmuebleDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }


}
