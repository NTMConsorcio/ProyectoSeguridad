package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.dto.UsuarioDTO;
import com.ntm.clienteadministrativo.dto.enums.Rol;
import com.ntm.clienteadministrativo.rest.UsuarioDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDTOService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UsuarioDTO usuario = buscarCuenta(username);
            if (usuario != null) {
                List<GrantedAuthority> permisos = new ArrayList();
                GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
                permisos.add(p);

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession(true);
                session.setAttribute("usuariosession", usuario);
                return new User(usuario.getCuenta(), " ", permisos);
            } else {
                return null;
            }
        } catch (ErrorServiceException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }


    }
}
