package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.CuentaCorreoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CuentaCorreoServiceImpl extends BaseServiceImpl<CuentaCorreo,String> implements CuentaCorreoService {

    private final CuentaCorreoRepository cuentacorreoRepository;

    public CuentaCorreoServiceImpl(BaseRepository<CuentaCorreo, String> baserepository, CuentaCorreoRepository cuentacorreoRepository) {super(baserepository);
        this.cuentacorreoRepository = cuentacorreoRepository;
    }

    public boolean validar(CuentaCorreo entity) {
        return true;
    }
}
