package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Localidad;
import com.ntm.appseguridad.entities.Servicio;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ServicioRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicioServiceImpl extends BaseServiceImpl<Servicio,String> implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(BaseRepository<Servicio, String> baserepository, ServicioRepository servicioRepository) {super(baserepository);
        this.servicioRepository = servicioRepository;
    }

    @Override
    public <D> List<D> convertToDtoList(List<Servicio> entities) {
        return List.of();
    }

    @Override
    public boolean validar(Servicio entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (caso.equals("SAVE")) {
                if (servicioRepository.existsByNombreAndEliminadoFalse(entity.getNombre())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                Servicio cc = servicioRepository.findByNombreAndEliminadoFalse(entity.getNombre());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El objeto especificado ya existe en el sistema");
                    }
                }
            }

            if (entity.getImagen() == null) {
                throw new ErrorServiceException("Debe indicar una imagen");
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
