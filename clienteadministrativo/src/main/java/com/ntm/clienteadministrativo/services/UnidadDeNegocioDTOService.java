package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.Rol;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import com.ntm.clienteadministrativo.rest.UnidadDeNegocioDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadDeNegocioDTOService {

    @Autowired
    UnidadDeNegocioDAORest dao;

    @Autowired
    ServicioDTOService servicioDTOService;
    @Autowired
    DireccionDTOService direccionDTOService;

    public void crear(String idServicio, String idDireccion,String nombre) throws ErrorServiceException {
        try {
            UnidadDeNegocioDTO unidadDeNegocio = new UnidadDeNegocioDTO();
            unidadDeNegocio.setNombre(nombre);

            ServicioDTO servicio = servicioDTOService.buscar(idServicio);
            unidadDeNegocio.setServicio(servicio);
            DireccionDTO direccion = direccionDTOService.buscar(idDireccion);
            unidadDeNegocio.setDireccion(direccion);
            dao.crear(UnidadDeNegocioDTO.class,unidadDeNegocio);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
    public void modificar(String id,String idServicio, String idDireccion,String nombre) throws ErrorServiceException {

        try {

            UnidadDeNegocioDTO unidadDeNegocio = new UnidadDeNegocioDTO();
            unidadDeNegocio.setNombre(nombre);
            ServicioDTO servicio = servicioDTOService.buscar(idServicio);
            unidadDeNegocio.setServicio(servicio);
            DireccionDTO direccion = direccionDTOService.buscar(idDireccion);
            unidadDeNegocio.setDireccion(direccion);
            unidadDeNegocio.setId(id);
            unidadDeNegocio.setEliminado(false);
            dao.actualizar(unidadDeNegocio);


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

    public List<UnidadDeNegocioDTO> getActivos() throws ErrorServiceException{
        try {
            List<UnidadDeNegocioDTO> unidades = dao.getActivos();
            return unidades;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UnidadDeNegocioDTO buscar(String id) throws ErrorServiceException{
        try {
            // Intentar buscar la unidad de negocio usando el DAO
            UnidadDeNegocioDTO unidad = dao.buscar(id);
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
