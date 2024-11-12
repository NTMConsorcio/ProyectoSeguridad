package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import com.ntm.clienteadministrativo.rest.ContactoTelefonicoDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoTelefonicoDTOService {
    @Autowired
    ContactoTelefonicoDAORest dao;

    public ContactoTelefonicoDTO crear(String observacion, TipoContactos tipoContacto, String telefono, TipoTelefono tipoTelefono) throws ErrorServiceException {

        try {
            ContactoTelefonicoDTO contacto = new ContactoTelefonicoDTO();
            contacto.setObservacion(observacion);
            contacto.setTipoContacto(tipoContacto);
            contacto.setTelefono(telefono);
            contacto.setTipoTelefono(tipoTelefono);
            contacto.setEliminado(false);
            dao.crearC(contacto);
            return contacto;
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ContactoTelefonicoDTO modificar(String id, String observacion, TipoContactos tipoContacto, String telefono, TipoTelefono tipoTelefono) throws ErrorServiceException {

        try {

            ContactoTelefonicoDTO contacto = new ContactoTelefonicoDTO();
            contacto.setId(id);
            contacto.setObservacion(observacion);
            contacto.setTipoContacto(tipoContacto);
            contacto.setTelefono(telefono);
            contacto.setTipoTelefono(tipoTelefono);
            contacto.setEliminado(false);
            dao.actualizar(contacto);
            return contacto;
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ContactoTelefonicoDTO buscar (String id) throws ErrorServiceException {
        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            ContactoTelefonicoDTO obj = dao.buscar(ContactoTelefonicoDTO.class, id);

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

    public List<ContactoTelefonicoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ContactoTelefonicoDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
