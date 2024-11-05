package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Visitante;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.VisitanteRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitanteServiceImpl extends BaseServiceImpl<Visitante,String> implements VisitanteService {
    private final VisitanteRepository visitanteRepository;

    public VisitanteServiceImpl(BaseRepository<Visitante, String> baseRepository, VisitanteRepository visitanteRepository) {
        super(baseRepository);
        this.visitanteRepository = visitanteRepository;
    }
}
