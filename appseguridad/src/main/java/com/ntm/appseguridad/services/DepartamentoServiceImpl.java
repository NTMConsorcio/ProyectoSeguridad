package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.mappers.DepartamentoMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.DepartamentoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartamentoServiceImpl extends BaseServiceImpl<Departamento,String> implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    public DepartamentoServiceImpl(BaseRepository<Departamento, String> baserepository, DepartamentoRepository departamentoRepository, DepartamentoMapper departamentoMapper) {super(baserepository);
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
    }

    @Override
    public <D> D convertToDto(Departamento entity) {
        return (D) departamentoMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<Departamento> entities) {
        return (List<D>) departamentoMapper.toDtoList(entities);
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
