package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Direccion;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends BaseRepository<Direccion, String> {
    Direccion findByCalleAndNumeracionAndEliminadoFalse(String calle, String numeracion);
    boolean existsByCalleAndNumeracionAndEliminadoFalse(String calle, String numeracion);
}
