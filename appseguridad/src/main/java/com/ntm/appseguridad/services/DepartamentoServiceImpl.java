package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.DepartamentoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;


@Service
public class DepartamentoServiceImpl extends BaseServiceImpl<Departamento,String> implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(BaseRepository<Departamento, String> baserepository, DepartamentoRepository departamentoRepository) {super(baserepository);
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public boolean validar(Departamento entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (caso.equals("SAVE")) {
                if (departamentoRepository.existsByNombreAndEliminadoFalse(entity.getNombre())) {
                    throw new ErrorServiceException("El departamento ya existe en el sistema");
                }
            } else {
                Departamento cc = departamentoRepository.findByNombreAndEliminadoFalse(entity.getNombre());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El departamento especificado ya existe en el sistema");
                    }
                }
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }
    }
}
