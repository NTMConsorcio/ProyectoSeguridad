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
    public boolean validar(Empresa entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (caso.equals("SAVE")) {
                if (empresaRepository.existsByNombreAndEliminadoFalse(entity.getNombre())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                Empresa cc = empresaRepository.findByNombreAndEliminadoFalse(entity.getNombre());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El objeto especificado ya existe en el sistema");
                    }
                }
            }

            if (entity.getDireccion() == null) {
                throw new ErrorServiceException("Debe indicar una direccion");
            }

            if (entity.getEmpresa() == null) {
                throw new ErrorServiceException("Debe indicar una empresa");
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }
    }
}
