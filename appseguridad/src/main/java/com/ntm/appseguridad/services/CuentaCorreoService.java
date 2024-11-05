package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;


public interface CuentaCorreoService extends BaseService<CuentaCorreo, String> {
    CuentaCorreo save(CuentaCorreo cuentaCorreo) throws Exception;
}
