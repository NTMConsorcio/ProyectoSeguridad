package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.services.CuentaCorreoServiceImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaCorreoRepository extends BaseRepository<CuentaCorreo, String> {
    CuentaCorreo findByCorreoAndEliminadoFalse(String correo);

    boolean existsByCorreoAndEliminadoFalse(String correo);

    boolean existsByEliminadoFalse();
    
}
