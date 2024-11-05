package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Empresa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends BaseRepository<Empresa, String> {
    Empresa findByNombreAndEliminadoFalse(String nombre);

    List<Empresa> findByNombreContainingAndEliminadoFalse(String nombre);

    boolean existsByNombreAndEliminadoFalse(String nombre);
}
