package com.ntm.appseguridad.services;

import com.ntm.appseguridad.business.domain.UnidadDeNegocio;

import java.util.List;

public interface UnidadDeNegocioService extends BaseService<UnidadDeNegocio, Long> {
    List<UnidadDeNegocio> search(String nombre) throws Exception;
}
