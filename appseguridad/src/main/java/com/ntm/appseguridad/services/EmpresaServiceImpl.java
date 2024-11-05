package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.entities.Empresa;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.EmpresaRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpresaServiceImpl extends BaseServiceImpl<Empresa,String> implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(BaseRepository<Empresa, String> baserepository, EmpresaRepository empresaRepository) {super(baserepository);
        this.empresaRepository = empresaRepository;
    }


    @Override
    public List<Empresa> search(String filtro) throws Exception {
        try {
            List<Empresa> unidades = empresaRepository.findByNombreContainingAndEliminadoFalse(filtro);
            return unidades;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean validar(Empresa entity) {
        return true;
    }
}
