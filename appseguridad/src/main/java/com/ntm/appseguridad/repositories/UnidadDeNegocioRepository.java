package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.UnidadDeNegocio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadDeNegocioRepository extends BaseRepository<UnidadDeNegocio, String> {
    List<UnidadDeNegocio> findByNombreContaining(String nombre);
}
