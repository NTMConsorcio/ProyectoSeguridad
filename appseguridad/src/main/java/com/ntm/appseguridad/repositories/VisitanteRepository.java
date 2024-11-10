package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Visitante;
import com.ntm.appseguridad.mappers.VisitanteMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitanteRepository extends BaseRepository<Visitante, String> {
    Visitante findByNumeroDeDocumentoAndEliminadoIsFalse(int numeroDeDocumento);
    VisitanteMapper findByNombreAndEliminadoIsFalse(String nombre);
    boolean existsByNumeroDeDocumentoAndEliminadoIsFalse(int numeroDeDocumento);
    boolean existsByNombreAndEliminadoIsFalse(String nombre);
}
