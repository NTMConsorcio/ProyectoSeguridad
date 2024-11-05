package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.DepartamentoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class DepartamentoServiceImpl extends BaseServiceImpl<Departamento,String> implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(BaseRepository<Departamento, String> baserepository, DepartamentoRepository departamentoRepository) {super(baserepository);
        this.departamentoRepository = departamentoRepository;
    }

    public boolean validar(Departamento entity) {
        return true;
    }
}
