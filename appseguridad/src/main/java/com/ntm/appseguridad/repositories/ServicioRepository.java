package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Pais;
import com.ntm.appseguridad.entities.Servicio;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends BaseRepository<Servicio, String> {
    Servicio findByNombreAndEliminadoFalse(String nombre);
    boolean existsByNombreAndEliminadoFalse(String nombre);
}
