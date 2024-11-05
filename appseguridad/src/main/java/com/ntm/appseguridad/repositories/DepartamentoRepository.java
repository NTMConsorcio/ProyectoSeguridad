package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Departamento;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends BaseRepository<Departamento, String> {
    Departamento findByNombreAndEliminadoFalse(String nombre);
}
