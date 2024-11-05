package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Empresa;


import java.util.List;

public interface EmpresaService extends BaseService<Empresa, String> {
    List<Empresa> search(String nombre) throws Exception;
    Empresa save(Empresa empresa) throws Exception;
}
