package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Localidad;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.LocalidadRepository;

import org.springframework.stereotype.Service;


@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad,String> implements LocalidadService {

    private final LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(BaseRepository<Localidad, String> baserepository, LocalidadRepository localidadRepository) {super(baserepository);
        this.localidadRepository = localidadRepository;
    }

    @Override
    public boolean validar(Localidad entity) {
        return true;
    }
}
