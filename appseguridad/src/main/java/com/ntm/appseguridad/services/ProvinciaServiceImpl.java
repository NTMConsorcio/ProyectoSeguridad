package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ProvinciaRepository;
import org.springframework.stereotype.Service;


@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia,String> implements ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaServiceImpl(BaseRepository<Provincia, String> baserepository, ProvinciaRepository provinciaRepository) {super(baserepository);
        this.provinciaRepository = provinciaRepository;
    }

    public boolean validar(Provincia entity) {
        return true;
    }
}
