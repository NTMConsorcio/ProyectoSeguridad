package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.Rol;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import com.ntm.clienteadministrativo.rest.EmpleadoDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoDTOService {
    @Autowired
    EmpleadoDAORest dao;

    @Autowired
    UsuarioDTOService serviceUsuario;

    @Autowired
    UnidadDeNegocioDTOService unidadService;

    @Autowired
    ContactoTelefonicoDTOService telService;

    @Autowired
    ContactoCorreoElectronicoDTOService correoService;
    @Autowired
    private ContactoTelefonicoDTOService contactoTelefonicoDTOService;

    public void crear(String documento, String nombre, String apellido, String numero, String correo, TipoEmpleado tipoEmpleado, String idUnidadDeNegocio) throws ErrorServiceException {
        try {
            EmpleadoDTO empleado = new EmpleadoDTO();
            empleado.setDocumento(Integer.parseInt(documento));
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setTipoEmpleado(tipoEmpleado);
            Rol rol = Rol.PERSONAL;
            if (tipoEmpleado.equals(TipoEmpleado.SUPERVISOR)){
                rol = Rol.ADMIN;
            }

            UnidadDeNegocioDTO unidad = unidadService.buscar(idUnidadDeNegocio);
            empleado.setUnidadDeNegocio(unidad);

            serviceUsuario.registrar(correo, documento, rol);
            UsuarioDTO usuario = serviceUsuario.buscarCuenta(correo);
            empleado.setUsuario(usuario);

            ContactoTelefonicoDTO tel = telService.crear("", TipoContactos.LABORAL ,numero, TipoTelefono.CELULAR);
            ContactoCorreoElectronicoDTO contacCorreo = correoService.crear("", TipoContactos.LABORAL, correo);
            List<ContactoDTO> contactos = new ArrayList<>();
            contactos.add(tel);
            contactos.add(contacCorreo);
            empleado.setContactos(contactos);

            dao.registrar(empleado);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id ,int documento, String nombre, String apellido, String numero, String correo, String legajo, TipoEmpleado tipoEmpleado, String idUnidadDeNegocio) throws ErrorServiceException {
        try {
            EmpleadoDTO empleado = new EmpleadoDTO();
            empleado.setId(id);
            empleado.setDocumento(documento);
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setTipoEmpleado(tipoEmpleado);
            Rol rol = Rol.PERSONAL;
            if (tipoEmpleado.equals("SUPERVISOR")){
                rol = Rol.ADMIN;
            }
//            UnidadDeNegocioDTO unidad = unidadService.buscar(idUnidadDeNegocio);
//            empleado.setUnidadDeNegocio(unidad);
//            UsuarioDTO usuario = serviceUsuario.buscarPorCorreo(correo);
//            empleado.setUsuario(usuario);
//            if telService.
//            ContactoTelefonicoDTO tel = telService.crear("", TipoContactos.LABORAL ,numero, TipoTelefono.CELULAR);
//
//            ContactoCorreoElectronicoDTO contacCorreo = correoService.crear("", TipoContactos.LABORAL, numero);
//            List<ContactoDTO> contactos = List.of();
//            contactos.add(tel);
//            contactos.add(contacCorreo);
//            empleado.setContactos(contactos);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<EmpleadoDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(EmpleadoDTO[].class);
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


}
