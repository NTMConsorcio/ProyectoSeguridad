package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Usuario;

public interface UsuarioService extends BaseService<Usuario, String> {
    Usuario searchByCuenta(String cuenta) throws Exception;
    Usuario searchByCuentaAndClave(String cuenta, String clave) throws Exception;
}
