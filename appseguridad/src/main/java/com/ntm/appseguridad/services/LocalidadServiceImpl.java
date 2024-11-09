package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Localidad;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.mappers.LocalidadMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.LocalidadRepository;

import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad,String> implements LocalidadService {

    private final LocalidadRepository localidadRepository;
    private final LocalidadMapper localidadMapper;

    public LocalidadServiceImpl(BaseRepository<Localidad, String> baserepository, LocalidadRepository localidadRepository, LocalidadMapper localidadMapper) {super(baserepository);
        this.localidadRepository = localidadRepository;
        this.localidadMapper = localidadMapper;
    }

    @Override
    public <D> D convertToDto(Localidad entity) {
        return null;
    }

    @Override
    public <D> List<D> convertToDtoList(List<Localidad> entities) {
        return (List<D>) localidadMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(Localidad entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (caso.equals("SAVE")) {
                if (localidadRepository.existsByNombreAndEliminadoFalse(entity.getNombre())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                Localidad cc = localidadRepository.findByNombreAndEliminadoFalse(entity.getNombre());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El objeto especificado ya existe en el sistema");
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
