package com.ntm.appseguridad.services;

import com.ntm.appseguridad.dto.PaisDTO;
import com.ntm.appseguridad.entities.UnidadDeNegocio;
import com.ntm.appseguridad.mappers.PaisMapper;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import com.ntm.appseguridad.entities.Pais;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaisServiceImpl extends BaseServiceImpl<Pais,String> implements PaisService {

    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    public PaisServiceImpl(BaseRepository<Pais, String> baserepository, PaisRepository paisRepository, PaisMapper paisMapper) {super(baserepository);
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
    }

    @Override
    public <D> List<D> convertToDtoList(List<Pais> entities) {
        return (List<D>) paisMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(Pais entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (caso.equals("SAVE")) {
                if (paisRepository.existsByNombreAndEliminadoFalse(entity.getNombre())) {
                    throw new ErrorServiceException("El pais ya existe en el sistema");
                }
            } else {
                Pais cc = paisRepository.findByNombreAndEliminadoFalse(entity.getNombre());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El pais especificado ya existe en el sistema");
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
