package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.entities.Direccion;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.DireccionRepository;
import org.springframework.stereotype.Service;


@Service
public class DireccionServiceImpl extends BaseServiceImpl<Direccion,String> implements DireccionService {

    private final DireccionRepository direccionRepository;

    public DireccionServiceImpl(BaseRepository<Direccion, String> baserepository, DireccionRepository direccionRepository) {super(baserepository);
        this.direccionRepository = direccionRepository;
    }

    public boolean validar(Direccion entity) {
        return true;
    }
}
