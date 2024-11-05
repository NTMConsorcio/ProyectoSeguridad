package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Localidad;
import com.ntm.appseguridad.entities.Servicio;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ServicioRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ServicioServiceImpl extends BaseServiceImpl<Servicio,String> implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(BaseRepository<Servicio, String> baserepository, ServicioRepository servicioRepository) {super(baserepository);
        this.servicioRepository = servicioRepository;
    }

    @Override
    public boolean validar(Servicio entity) {
        return true;
    }
}
