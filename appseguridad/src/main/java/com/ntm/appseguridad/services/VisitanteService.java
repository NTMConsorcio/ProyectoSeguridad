package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Base;
import com.ntm.appseguridad.entities.Visitante;

public interface VisitanteService extends BaseService<Visitante, String> {
    Visitante findByNumeroDeDocumentoAndEliminadoIsFalse(int numeroDeDocumento) throws Exception;
}
