package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.UnidadDeNegocio;

import java.util.List;

public interface UnidadDeNegocioService extends BaseService<UnidadDeNegocio, String> {
    List<UnidadDeNegocio> search(String nombre) throws Exception;
}
