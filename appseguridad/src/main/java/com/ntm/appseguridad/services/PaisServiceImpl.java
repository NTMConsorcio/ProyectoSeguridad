package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Pais;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.PaisRepository;
import org.springframework.stereotype.Service;


@Service
public class PaisServiceImpl extends BaseServiceImpl<Pais,String> implements PaisService {

    private final PaisRepository paisRepository;

    public PaisServiceImpl(BaseRepository<Pais, String> baserepository, PaisRepository paisRepository) {super(baserepository);
        this.paisRepository = paisRepository;
    }

    @Override
    public boolean validar(Pais entity) {
        return true;
    }
}
