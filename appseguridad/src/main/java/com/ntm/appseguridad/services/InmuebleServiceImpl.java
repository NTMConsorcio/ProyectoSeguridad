package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.entities.UnidadDeNegocio;
import com.ntm.appseguridad.mappers.InmuebleMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.InmuebleRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InmuebleServiceImpl extends BaseServiceImpl<Inmueble,String> implements InmuebleService {
    private final InmuebleRepository inmuebleRepository;
    private final InmuebleMapper inmuebleMapper;

    public InmuebleServiceImpl(BaseRepository<Inmueble, String> baseRepository, InmuebleRepository inmuebleRepository, InmuebleMapper inmuebleMapper) {
        super(baseRepository);
        this.inmuebleRepository = inmuebleRepository;
        this.inmuebleMapper = inmuebleMapper;
    }


    @Override
    public <D> D convertToDto(Inmueble entity) {
        return (D) inmuebleMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<Inmueble> entities) {
        return List.of();
    }

    @Override
    public boolean validar(Inmueble entity, String caso) throws Exception {
        try {
            if (entity.getNumeracion() == null || entity.getNumeracion().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }
            if (entity.getEstadoInmueble() == null) {
                throw new ErrorServiceException("Debe indicar el estado");
            }


            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El inmueble ya existe en el sistema");
                }
                if (inmuebleRepository.findByNumeracionAndPisoAndAndDptoAndEliminadoIsFalse(entity.getNumeracion(), entity.getPiso(), entity.getDpto()) != null) {
                    throw new ErrorServiceException("El inmueble ya existe en el sistema");
                }
            } else {
                Optional<Inmueble> uu = repository.findByIdAndEliminadoFalse(entity.getId());
                if (uu.isPresent()) {
                    Inmueble e = uu.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El inmueble especificado no existe en el sistema");
                    }
                }
            }
            return true;
        }catch (ErrorServiceException ex){
            throw ex;
        }catch (Exception ex){
            throw new ErrorServiceException("Error de sistema");
        }
    }


    @Override
    public Inmueble findByNumeracionAndPisoAndAndDptoAndEliminadoIsFalse(String nombre, String piso, String dpto) throws Exception {
        try {
            Inmueble inmueble = inmuebleRepository.findByNumeracionAndPisoAndAndDptoAndEliminadoIsFalse(nombre, piso, dpto);
            return inmueble;

        }catch (Exception e){
            throw new ErrorServiceException(e.getMessage());
        }
    }
}
