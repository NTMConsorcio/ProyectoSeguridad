package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.ContactoDTO;
import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.dto.EmpresaDTO;
import com.ntm.clienteadministrativo.rest.EmpresaDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaDTOService {
    @Autowired
    private EmpresaDAORest dao;

    @Autowired
    private DireccionDTOService serviceDireccion;

    @Autowired
    private ContactoTelefonicoDTOService serviceContactoTelefonico;

    @Autowired
    private ContactoCorreoElectronicoDTOService serviceContactoCorreo;

    public void crear(String nombre, String idDireccion, String idContacto, String tipoContacto) throws ErrorServiceException {

        try {
            EmpresaDTO empresa = new EmpresaDTO();
            empresa.setNombre(nombre);

            DireccionDTO direccion = serviceDireccion.buscar(idDireccion);
            empresa.setDireccion(direccion);
            ContactoDTO contacto;
            if (tipoContacto.equals("TELEFONICO")) {
                contacto = serviceContactoTelefonico.buscar(idContacto);
            } else {
                contacto = serviceContactoCorreo.buscar(idContacto);

            }
            empresa.setContacto(contacto);
            dao.crear(EmpresaDTO.class, empresa);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String nombre, String idDireccion, String idContacto, String tipoContacto) throws ErrorServiceException {

        try {

            EmpresaDTO empresa = new EmpresaDTO();
            empresa.setId(id);
            empresa.setNombre(nombre);

            DireccionDTO direccion = serviceDireccion.buscar(idDireccion);
            empresa.setDireccion(direccion);

            ContactoDTO contacto;
            if (tipoContacto.equals("TELEFONICO")) {
                contacto = serviceContactoTelefonico.buscar(idContacto);
            } else {
                contacto = serviceContactoCorreo.buscar(idContacto);

            }
            empresa.setContacto(contacto);

            dao.actualizar(empresa);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public EmpresaDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            EmpresaDTO obj = dao.buscar(EmpresaDTO.class, id);

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

    public List<EmpresaDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(EmpresaDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
