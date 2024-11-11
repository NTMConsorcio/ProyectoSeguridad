package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDTOService {
    public UsuarioDTO crear(String cuenta, String clave) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setClave(clave);
        usuario.setCuenta(cuenta);
        return usuario;
    }
}
