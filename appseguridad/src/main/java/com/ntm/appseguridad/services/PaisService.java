package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Pais;

public interface PaisService extends BaseService<Pais, String> {
    Pais save(Pais pais) throws Exception;
}
