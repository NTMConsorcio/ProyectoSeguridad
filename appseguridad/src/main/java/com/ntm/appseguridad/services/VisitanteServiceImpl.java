package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.entities.Visitante;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.VisitanteRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitanteServiceImpl extends BaseServiceImpl<Visitante,String> implements VisitanteService {
    private final VisitanteRepository visitanteRepository;

    public VisitanteServiceImpl(BaseRepository<Visitante, String> baseRepository, VisitanteRepository visitanteRepository) {
        super(baseRepository);
        this.visitanteRepository = visitanteRepository;
    }


    @Override
    public boolean validar(Visitante entity, String caso) throws Exception {
        try {

            if (entity.getNombre()==null || entity.getNombre().equals("")) {
                throw new Exception("El nombre no puede estar vacio");
            }
            if (entity.getApellido()==null || entity.getApellido().equals("")) {
                throw new Exception("El apellido no puede estar vacio");
            }
            if (entity.getNumeroDeDocumento()==0){
                throw new Exception("El numero de documento no puede estar vacio");
            }

            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El visitante ya existe en el sistema");
                }
                if (visitanteRepository.findByNumeroDeDocumentoAndEliminadoIsFalse(entity.getNumeroDeDocumento()) != null) {
                    throw new ErrorServiceException("El visitante ya existe en el sistema");
                }
            } else {
                Optional<Visitante> uu = repository.findByIdAndEliminadoFalse(entity.getId());
                if (uu.isPresent()) {
                    Visitante e = uu.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El visitante especificado no existe en el sistema");
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

    @Override
    public Visitante findByNumeroDeDocumentoAndEliminadoIsFalse(int numeroDeDocumento) throws Exception {
        try {
            Visitante visitante = visitanteRepository.findByNumeroDeDocumentoAndEliminadoIsFalse(numeroDeDocumento);
            return visitante;
        }catch (Exception e){
            throw new ErrorServiceException("Error de sistemas");
        }
    }
}
