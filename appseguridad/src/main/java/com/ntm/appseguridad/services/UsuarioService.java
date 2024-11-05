package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Usuario;

public interface UsuarioService extends BaseService<Usuario, String> {
    Usuario searchByEmail(String email) throws Exception;
    Usuario searchByEmailAndClave(String email, String clave) throws Exception;
}
