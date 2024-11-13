package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.Pais;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;
import com.ntm.appseguridad.mappers.ProvinciaMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ProvinciaRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia, String> implements ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    public ProvinciaServiceImpl(BaseRepository<Provincia, String> baserepository, ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        super(baserepository);
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    @Override
    public <D> D convertToDto(Provincia entity) {
        return (D) provinciaMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<Provincia> entities) {
        return (List<D>) provinciaMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(Provincia entity, String caso) throws ErrorServiceException {
        try {
            System.out.println(entity.getPais().getNombre());
            System.out.println(entity.getPais().getId());
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (entity.getPais() == null) {
                throw new ErrorServiceException("Debe indicar el pais");
            }

            if (caso.equals("SAVE")) {
                if (provinciaRepository.existsByNombreAndEliminadoFalse(entity.getNombre())) {
                    throw new ErrorServiceException("El objeto ya existe en el sistema");
                }
            } else {
                Provincia cc = provinciaRepository.findByNombreAndEliminadoFalse(entity.getNombre());
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
