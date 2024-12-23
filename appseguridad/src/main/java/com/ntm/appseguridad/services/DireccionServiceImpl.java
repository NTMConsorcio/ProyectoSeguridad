package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.entities.Direccion;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.mappers.DireccionMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.DireccionRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DireccionServiceImpl extends BaseServiceImpl<Direccion,String> implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final DireccionMapper direccionMapper;

    public DireccionServiceImpl(BaseRepository<Direccion, String> baserepository, DireccionRepository direccionRepository, DireccionMapper direccionMapper) {super(baserepository);
        this.direccionRepository = direccionRepository;
        this.direccionMapper = direccionMapper;
    }

    @Override
    public <D> D convertToDto(Direccion entity) {
        return (D) direccionMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<Direccion> entities) {
        return (List<D>) direccionMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(Direccion entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getCalle() == null || entity.getCalle().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la calle");
            }

            if (entity.getNumeracion() == null || entity.getNumeracion().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la numeración");
            }

            if (entity.getLatitud() == null || entity.getLatitud().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la latitud");
            }

            if (entity.getLongitud() == null || entity.getLongitud().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la longitud");
            }

            if (caso.equals("SAVE")) {
                if (direccionRepository.existsByCalleAndNumeracionAndEliminadoFalse(entity.getCalle(), entity.getNumeracion())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                Direccion cc = direccionRepository.findByCalleAndNumeracionAndEliminadoFalse(entity.getCalle(), entity.getNumeracion());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El objeto especificado ya existe en el sistema");
                    }
                }
            }

            if (entity.getLocalidad() == null) {
                throw new ErrorServiceException("Debe indicar una localidad");
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }
    }
}
