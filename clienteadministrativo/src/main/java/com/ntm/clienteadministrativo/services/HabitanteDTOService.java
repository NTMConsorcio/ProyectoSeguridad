package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.Rol;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import com.ntm.clienteadministrativo.rest.HabitanteDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabitanteDTOService {
    @Autowired
    HabitanteDAORest dao;

    @Autowired
    UsuarioDTOService serviceUsuario;

    @Autowired
    InmuebleDTOService inmuebleService;

    @Autowired
    ContactoTelefonicoDTOService telService;

    @Autowired
    ContactoCorreoElectronicoDTOService correoService;
    @Autowired
    private ContactoTelefonicoDTOService contactoTelefonicoDTOService;

    public void crear(String documento, String nombre, String apellido, String numero, String correo, String idInmueble) throws ErrorServiceException {
        try {
            HabitanteDTO habitante = new HabitanteDTO();
            habitante.setDocumento(Integer.parseInt(documento));
            habitante.setNombre(nombre);
            habitante.setApellido(apellido);

            InmuebleDTO inmueble = inmuebleService.buscar(idInmueble);
            habitante.setInmueble(inmueble);

            serviceUsuario.registrar(correo, documento, Rol.HABITANTE);
            UsuarioDTO usuario = serviceUsuario.buscarCuenta(correo);
            habitante.setUsuario(usuario);

            ContactoTelefonicoDTO tel = telService.crear("", TipoContactos.PERSONAL ,numero, TipoTelefono.CELULAR);
            ContactoCorreoElectronicoDTO contacCorreo = correoService.crear("", TipoContactos.PERSONAL, correo);
            List<ContactoDTO> contactos = new ArrayList<>();
            contactos.add(tel);
            contactos.add(contacCorreo);
            habitante.setContactos(contactos);

            dao.registrar(habitante);
        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id ,String documento, String nombre, String apellido, String idTelAnt, String idCorrAnt, String numero, String correo, String idInmueble) throws ErrorServiceException {
        try {
            HabitanteDTO habitante = new HabitanteDTO();
            habitante.setId(id);
            habitante.setDocumento(Integer.parseInt(documento));
            habitante.setNombre(nombre);
            habitante.setApellido(apellido);
            InmuebleDTO inmueble = inmuebleService.buscar(idInmueble);
            habitante.setInmueble(inmueble);
            UsuarioDTO usuario = serviceUsuario.buscarPorIdPersona(id);
            habitante.setUsuario(usuario);

            ContactoTelefonicoDTO tel = telService.buscar(idTelAnt);
            if (!tel.getTelefono().equals(numero)) {
                tel = telService.modificar(idTelAnt, "", TipoContactos.PERSONAL ,numero, TipoTelefono.CELULAR);
            }

            ContactoCorreoElectronicoDTO contacCorreo = correoService.buscar(idCorrAnt);
            if (!contacCorreo.getEmail().equals(correo)) {
                contacCorreo = correoService.modificar(idCorrAnt, "", TipoContactos.PERSONAL, correo);
            }
            List<ContactoDTO> contactos = new ArrayList<>();
            contactos.add(tel);
            contactos.add(contacCorreo);
            habitante.setContactos(contactos);

            dao.actualizar(habitante);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<HabitanteDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(HabitanteDTO[].class);
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

    public HabitanteDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            HabitanteDTO obj = dao.buscar(HabitanteDTO.class, id);

            return obj;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
