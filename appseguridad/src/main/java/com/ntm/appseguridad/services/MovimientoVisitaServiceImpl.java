package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.MovimientoVisita;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.MovimientoVisitaRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimientoVisitaServiceImpl extends BaseServiceImpl<MovimientoVisita,String> implements MovimientoVisitaService {
    private final MovimientoVisitaRepository movimientoVisitaRepository;

    public MovimientoVisitaServiceImpl(BaseRepository<MovimientoVisita,String> baseRepository, MovimientoVisitaRepository movimientoVisitaRepository) {
        super(baseRepository);
        this.movimientoVisitaRepository = movimientoVisitaRepository;
    }
}
