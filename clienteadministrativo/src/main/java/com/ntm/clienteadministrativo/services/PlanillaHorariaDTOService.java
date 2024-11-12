package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.EstadoAsistencia;
import com.ntm.clienteadministrativo.dto.enums.Rol;
import com.ntm.clienteadministrativo.rest.PlanillaHorariaDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanillaHorariaDTOService {
    @Autowired
    PlanillaHorariaDAORest dao;

    @Autowired
    UsuarioDTOService serviceUsuario;

    @Autowired
    EmpleadoDTOService serviceEmpleado;

    @Autowired
    UnidadDeNegocioDTOService unidadService;

    @Autowired
    ContactoTelefonicoDTOService telService;

    @Autowired
    ContactoCorreoElectronicoDTOService correoService;
    @Autowired
    private ContactoTelefonicoDTOService contactoTelefonicoDTOService;

    public void crear(Date entrada, Date salida, EstadoAsistencia estado, String idEmpleado) throws ErrorServiceException {
        try {
            PlanillaHorariaDTO planilla = new PlanillaHorariaDTO();
            planilla.setEntrada(entrada);
            planilla.setSalida(salida);
            planilla.setEstadoAsistencia(estado);

            EmpleadoDTO empleado = serviceEmpleado.buscar(idEmpleado);
            planilla.setEmpleado(empleado);

            dao.registrar(planilla);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, Date entrada, Date salida, EstadoAsistencia estado, String idEmpleado) throws ErrorServiceException {
        try {
            PlanillaHorariaDTO planilla = new PlanillaHorariaDTO();
            planilla.setId(id);
            planilla.setEntrada(entrada);
            planilla.setSalida(salida);
            planilla.setEstadoAsistencia(estado);

            EmpleadoDTO empleado = serviceEmpleado.buscar(idEmpleado);
            planilla.setEmpleado(empleado);

            dao.actualizar(planilla);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<PlanillaHorariaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(PlanillaHorariaDTO[].class);
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

    public PlanillaHorariaDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            PlanillaHorariaDTO obj = dao.buscar(PlanillaHorariaDTO.class, id);

            return obj;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
