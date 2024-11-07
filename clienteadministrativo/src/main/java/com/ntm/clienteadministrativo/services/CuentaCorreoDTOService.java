package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.EmpresaDTO;
import com.ntm.clienteadministrativo.dto.CuentaCorreoDTO;
import com.ntm.clienteadministrativo.rest.CuentaCorreoDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaCorreoDTOService {
    @Autowired
    CuentaCorreoDAORest dao;

    @Autowired
    EmpresaDTOService service;

    public void crear(String correo, String clave, String puerto, String smtp, boolean tls, String idEmpresa) throws ErrorServiceException {

        try {
            CuentaCorreoDTO cuentaCorreo = new CuentaCorreoDTO();
            cuentaCorreo.setCorreo(correo);
            cuentaCorreo.setTls(tls);
            cuentaCorreo.setSmtp(smtp);
            cuentaCorreo.setClave(clave);
            cuentaCorreo.setPuerto(puerto);
            cuentaCorreo.setEliminado(false);
            EmpresaDTO empresa = service.buscar(idEmpresa);
            cuentaCorreo.setEmpresa(empresa);
            dao.crear(CuentaCorreoDTO.class, cuentaCorreo);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, String correo, String clave, String puerto, String smtp, boolean tls, String idEmpresa) throws ErrorServiceException {

        try {
            CuentaCorreoDTO cuentaCorreo = new CuentaCorreoDTO();
            cuentaCorreo.setId(id);
            cuentaCorreo.setCorreo(correo);
            cuentaCorreo.setTls(tls);
            cuentaCorreo.setSmtp(smtp);
            cuentaCorreo.setClave(clave);
            cuentaCorreo.setPuerto(puerto);
            cuentaCorreo.setEliminado(false);
            EmpresaDTO empresa = service.buscar(idEmpresa);
            cuentaCorreo.setEmpresa(empresa);
            dao.actualizar(cuentaCorreo);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public CuentaCorreoDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            CuentaCorreoDTO obj = dao.buscar(CuentaCorreoDTO.class, id);

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

    public List<CuentaCorreoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(CuentaCorreoDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
