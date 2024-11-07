package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Localidad;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, String> {
    Localidad findByNombreAndEliminadoFalse(String nombre);
    boolean existsByNombreAndEliminadoFalse(String nombre);

}
