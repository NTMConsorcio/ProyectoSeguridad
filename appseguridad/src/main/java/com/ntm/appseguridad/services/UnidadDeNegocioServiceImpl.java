package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.UnidadDeNegocio;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.UnidadDeNegocioRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UnidadDeNegocioServiceImpl extends BaseServiceImpl<UnidadDeNegocio,String> implements UnidadDeNegocioService {

    private final UnidadDeNegocioRepository unidadDeNegocioRepository;

    public UnidadDeNegocioServiceImpl(BaseRepository<UnidadDeNegocio, String> baserepository, UnidadDeNegocioRepository unidadDeNegocioRepository) {super(baserepository);
        this.unidadDeNegocioRepository = unidadDeNegocioRepository;
    }


    @Override
    public List<UnidadDeNegocio> search(String filtro) throws Exception {
        try {
            List<UnidadDeNegocio> unidades = unidadDeNegocioRepository.findByNombreContaining(filtro);
            return unidades;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
