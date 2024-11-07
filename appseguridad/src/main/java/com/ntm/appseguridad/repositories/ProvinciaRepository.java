package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Provincia;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends BaseRepository<Provincia, String> {
    Provincia findByNombreAndEliminadoFalse(String nombre);
    boolean existsByNombreAndEliminadoFalse(String nombre);

}
