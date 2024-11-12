package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.EmpresaDTO;
import com.ntm.clienteadministrativo.dto.ImagenDTO;
import com.ntm.clienteadministrativo.dto.ServicioDTO;
import com.ntm.clienteadministrativo.rest.ServicioDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDTOService {
    @Autowired
    ServicioDAORest dao;

    @Autowired
    ImagenDTOService serviceImagen;

    @Autowired
    EmpresaDTOService serviceEmpresa;

    public void crear(String nombre, String idImagen, String idEmpresa) throws ErrorServiceException {

        try {
            ServicioDTO servicio = new ServicioDTO();
            servicio.setNombre(nombre);
            ImagenDTO imagen = serviceImagen.buscar(idImagen);
            servicio.setImagen(imagen);
            EmpresaDTO empresa = serviceEmpresa.buscar(idEmpresa);
            servicio.setEmpresa(empresa);
            dao.crear(ServicioDTO.class, servicio);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String nombre, String idImagen, String idEmpresa) throws ErrorServiceException {

        try {
            ServicioDTO servicio = new ServicioDTO();
            servicio.setId(id);
            servicio.setNombre(nombre);
            ImagenDTO imagen = serviceImagen.buscar(idImagen);
            servicio.setImagen(imagen);
            EmpresaDTO empresa = serviceEmpresa.buscar(idEmpresa);
            servicio.setEmpresa(empresa);
            dao.actualizar(servicio);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ServicioDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            ServicioDTO obj = dao.buscar(ServicioDTO.class, id);

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

    public List<ServicioDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ServicioDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
