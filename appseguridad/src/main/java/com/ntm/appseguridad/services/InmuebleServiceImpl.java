package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.InmuebleRepository;
import org.springframework.stereotype.Service;

@Service
public class InmuebleServiceImpl extends BaseServiceImpl<Inmueble,String> implements InmuebleService {
    private final InmuebleRepository inmuebleRepository;

    public InmuebleServiceImpl(BaseRepository<Inmueble, String> baseRepository,InmuebleRepository inmuebleRepository) {
        super(baseRepository);
        this.inmuebleRepository = inmuebleRepository;
    }
}
