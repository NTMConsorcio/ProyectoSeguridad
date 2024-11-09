package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.ContactoCorreoElectronicoDTO;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.rest.ContactoCorreoElectronicoDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoCorreoElectronicoDTOService {
    @Autowired
    ContactoCorreoElectronicoDAORest dao;

    public ContactoCorreoElectronicoDTO crear(String observacion, TipoContactos tipoContacto, String correo) throws ErrorServiceException {

        try {
            ContactoCorreoElectronicoDTO contactoCorreo = new ContactoCorreoElectronicoDTO();
            contactoCorreo.setEmail(correo);
            contactoCorreo.setTipoContacto(tipoContacto);
            contactoCorreo.setObservacion(observacion);
            contactoCorreo.setEliminado(false);
            dao.crearC(contactoCorreo);
            return contactoCorreo;

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String observacion, TipoContactos tipoContacto, String correo) throws ErrorServiceException {

        try {

            ContactoCorreoElectronicoDTO contactoCorreo = new ContactoCorreoElectronicoDTO();
            contactoCorreo.setId(id);
            contactoCorreo.setEmail(correo);
            contactoCorreo.setTipoContacto(tipoContacto);
            contactoCorreo.setObservacion(observacion);
            contactoCorreo.setEliminado(false);
            dao.actualizar(contactoCorreo);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ContactoCorreoElectronicoDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            ContactoCorreoElectronicoDTO obj = dao.buscar(ContactoCorreoElectronicoDTO.class, id);

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

    public List<ContactoCorreoElectronicoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ContactoCorreoElectronicoDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
