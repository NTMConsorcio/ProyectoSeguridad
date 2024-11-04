package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.business.domain.UnidadDeNegocio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadDeNegocioRepository extends BaseRepository<UnidadDeNegocio, Long> {
    List<UnidadDeNegocio> findByNombreContaining(String nombre);
}
