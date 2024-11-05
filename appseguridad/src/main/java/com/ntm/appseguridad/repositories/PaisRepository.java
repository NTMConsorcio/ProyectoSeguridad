package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Pais;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends BaseRepository<Pais, String> {
    Pais findByNombreAndEliminadoFalse(String nombre);
}
