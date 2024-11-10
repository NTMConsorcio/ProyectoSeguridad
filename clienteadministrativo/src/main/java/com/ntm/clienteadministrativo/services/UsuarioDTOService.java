package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.dto.UsuarioDTO;
import com.ntm.clienteadministrativo.dto.enums.Rol;
import com.ntm.clienteadministrativo.rest.UsuarioDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDTOService {

    @Autowired
    public UsuarioDAORest dao;

    public UsuarioDTO registrar(String correo, String clave, Rol rol) throws ErrorServiceException {
        try {
            //validaciones
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setCuenta(correo);
            usuario.setClave(clave);
            usuario.setRol(rol);
            dao.registrar(usuario);
            return usuario;

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscarCuenta (String cuenta) throws ErrorServiceException {

        try {

            if (cuenta == null) {
                throw new ErrorServiceException("Debe indicar la cuenta");
            }

            UsuarioDTO obj = dao.buscarCuenta(cuenta);

            return obj;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
