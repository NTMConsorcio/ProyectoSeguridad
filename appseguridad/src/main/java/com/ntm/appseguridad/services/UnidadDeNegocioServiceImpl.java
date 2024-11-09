package com.ntm.appseguridad.services;

import com.ntm.appseguridad.dto.UnidadDeNegocioDTO;
import com.ntm.appseguridad.entities.UnidadDeNegocio;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.mappers.UnidadDeNegocioMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.UnidadDeNegocioRepository;

import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UnidadDeNegocioServiceImpl extends BaseServiceImpl<UnidadDeNegocio,String> implements UnidadDeNegocioService {

    private final UnidadDeNegocioRepository unidadDeNegocioRepository;
    private final UnidadDeNegocioMapper unidadDeNegocioMapper;

    public UnidadDeNegocioServiceImpl(BaseRepository<UnidadDeNegocio, String> baserepository, UnidadDeNegocioRepository unidadDeNegocioRepository, UnidadDeNegocioMapper unidadDeNegocioMapper) {super(baserepository);
        this.unidadDeNegocioRepository = unidadDeNegocioRepository;
        this.unidadDeNegocioMapper = unidadDeNegocioMapper;
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
    public <D> D convertToDto(UnidadDeNegocio entity) {
        return (D) unidadDeNegocioMapper.toUnidadDeNegocioDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<UnidadDeNegocio> entities) {
        return (List<D>) unidadDeNegocioMapper.toDtoList(entities);
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

//    public List<UnidadDeNegocioDTO> listarUnidadDeNegocioActivo() throws Exception {
//        List<UnidadDeNegocio> unidades = findAll();
//        return unidadDeNegocioMapper.toDtoList(unidades);  // Convertir la lista de entidades a DTOs
//    }
}
