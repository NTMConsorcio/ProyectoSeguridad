package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.CuentaCorreo;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaCorreoRepository extends BaseRepository<CuentaCorreo, String> {
    CuentaCorreo findByCorreoAndEliminadoFalse(String correo);

}
