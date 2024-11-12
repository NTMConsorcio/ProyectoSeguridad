package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.EstadoMovimiento;
import com.ntm.clienteadministrativo.dto.enums.TipoMovilidad;
import com.ntm.clienteadministrativo.dto.enums.TipoMovimiento;
import com.ntm.clienteadministrativo.rest.MovimientoVisitaDAORest;
import com.ntm.clienteadministrativo.rest.PaisDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoVisitaDTOService {
    @Autowired
    MovimientoVisitaDAORest dao;

    @Autowired
    InmuebleDTOService inmuebleService;
    @Autowired
    VisitanteDTOService visitanteService;



    public void crear(TipoMovilidad tipoMovilidad, TipoMovimiento tipoMovimiento, Date fecha, String observacion, EstadoMovimiento estadoMovimiento, String descripcionMovilidad, String idInmueble, String idVisitante) throws ErrorServiceException {

        try {

            MovimientoVisitaDTO movimiento = new MovimientoVisitaDTO();
            movimiento.setTipoMovilidad(tipoMovilidad);
            movimiento.setTipoMovimiento(tipoMovimiento);
            movimiento.setObservacion(observacion);
            movimiento.setEstadoMovimiento(estadoMovimiento);
            movimiento.setDescripcionMovilidad(descripcionMovilidad);
            movimiento.setFechaMovimiento(fecha);
            InmuebleDTO inmueble = inmuebleService.buscar(idInmueble);
            VisitanteDTO visitante = visitanteService.buscar(idVisitante);
            movimiento.setInmueble(inmueble);
            movimiento.setVisitante(visitante);
            dao.crear(MovimientoVisitaDTO.class, movimiento);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, TipoMovilidad tipoMovilidad, TipoMovimiento tipoMovimiento, Date fecha, String observacion, EstadoMovimiento estadoMovimiento, String descripcionMovilidad, String idInmueble, String idVisitante) throws ErrorServiceException {

        try {

            MovimientoVisitaDTO movimiento = new MovimientoVisitaDTO();
            movimiento.setTipoMovilidad(tipoMovilidad);
            movimiento.setTipoMovimiento(tipoMovimiento);
            movimiento.setObservacion(observacion);
            movimiento.setEstadoMovimiento(estadoMovimiento);
            movimiento.setDescripcionMovilidad(descripcionMovilidad);
            movimiento.setFechaMovimiento(fecha);
            InmuebleDTO inmueble = inmuebleService.buscar(idInmueble);
            movimiento.setInmueble(inmueble);
            VisitanteDTO visitante = visitanteService.buscar(idVisitante);
            movimiento.setVisitante(visitante);
            movimiento.setId(id);
            movimiento.setEliminado(false);
            dao.actualizar(movimiento);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public MovimientoVisitaDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            MovimientoVisitaDTO obj = dao.buscar(MovimientoVisitaDTO.class, id);
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

    public List<MovimientoVisitaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(MovimientoVisitaDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
