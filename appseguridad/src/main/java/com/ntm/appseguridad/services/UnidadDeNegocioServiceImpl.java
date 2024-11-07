package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.UnidadDeNegocio;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.UnidadDeNegocioRepository;

import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UnidadDeNegocioServiceImpl extends BaseServiceImpl<UnidadDeNegocio,String> implements UnidadDeNegocioService {

    private final UnidadDeNegocioRepository unidadDeNegocioRepository;

    public UnidadDeNegocioServiceImpl(BaseRepository<UnidadDeNegocio, String> baserepository, UnidadDeNegocioRepository unidadDeNegocioRepository) {super(baserepository);
        this.unidadDeNegocioRepository = unidadDeNegocioRepository;
    }


    @Override
    public List<UnidadDeNegocio> searchByNombreContaining(String filtro) throws Exception {
        try {
            List<UnidadDeNegocio> unidades = unidadDeNegocioRepository.findByNombreContaining(filtro);
            return unidades;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UnidadDeNegocio searchByNombre(String nombre) throws Exception {
        try {
            UnidadDeNegocio unidadDeNegocio = unidadDeNegocioRepository.findByNombreAndEliminadoIsFalse(nombre);
            return unidadDeNegocio;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean validar(UnidadDeNegocio entity, String caso) throws Exception {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("La unidad de negocio ya existe en el sistema");
                }
                if (unidadDeNegocioRepository.findByNombreAndEliminadoIsFalse(entity.getNombre()) != null) {
                    throw new ErrorServiceException("La unidad de negocio ya existe en el sistema");
                }
            } else {
                Optional<UnidadDeNegocio> uu = repository.findByIdAndEliminadoFalse(entity.getId());
                if (uu.isPresent()) {
                    UnidadDeNegocio e = uu.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("La unidad de negocio especificada no existe en el sistema");
                    }
                }
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistema");
        }
    }
}
