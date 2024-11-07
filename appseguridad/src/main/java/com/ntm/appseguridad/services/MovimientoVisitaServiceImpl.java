package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.entities.MovimientoVisita;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.entities.Visitante;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.MovimientoVisitaRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MovimientoVisitaServiceImpl extends BaseServiceImpl<MovimientoVisita,String> implements MovimientoVisitaService {
    private final MovimientoVisitaRepository movimientoVisitaRepository;

    public MovimientoVisitaServiceImpl(BaseRepository<MovimientoVisita,String> baseRepository, MovimientoVisitaRepository movimientoVisitaRepository) {
        super(baseRepository);
        this.movimientoVisitaRepository = movimientoVisitaRepository;
    }


    @Override
    public boolean validar(MovimientoVisita entity, String caso) throws Exception {
        try {
            if (entity.getFechaMovimiento() == null || entity.getFechaMovimiento().before(new Date())) {
                throw new ErrorServiceException("Debe indicar la fecha del movimiento");
            }
            if (entity.getDescripcionMovilidad() == null || entity.getDescripcionMovilidad().equals("")) {
                throw new ErrorServiceException("Debe indicar la descripicion del movimiento");
            }
            if (entity.getTipoMovimiento() == null ) {
                throw new ErrorServiceException("Debe indicar el tipo de movimiento");
            }
            if (entity.getTipoMovilidad() == null) {
                throw new ErrorServiceException("Debe indicar el tipo de movilidad");
            }
            if (entity.getEstadoMovimiento() == null) {
                throw new ErrorServiceException("Debe indicar el estado de movimiento");
            }
            if (entity.getInmueble() == null || entity.getInmueble().equals("")) {
                throw new ErrorServiceException("Debe indicar el inmueble");
            }
            if (entity.getVisitante() == null || entity.getVisitante().equals("")) {
                throw new ErrorServiceException("Debe indicar el visitante");
            }


            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El Inmueble ya existe en el sistema");
                }
                if (movimientoVisitaRepository.findByFechaMovimientoAndInmuebleAndVisitanteAndEliminadoIsFalse(entity.getFechaMovimiento(),entity.getInmueble(),entity.getVisitante()) != null) {
                    throw new ErrorServiceException("El Inmueble ya existe en el sistema");
                }
            } else {
                Optional<MovimientoVisita> uu = repository.findByIdAndEliminadoFalse(entity.getId());
                if (uu.isPresent()) {
                    MovimientoVisita e = uu.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El movimiento especificado no existe en el sistema");
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
    public MovimientoVisita findByFechaMovimientoAndInmuebleAndVisitanteAndEliminadoIsFalse(Date fechaDeMoviiento, Inmueble inmueble, Visitante visitante) throws Exception {
        try {
            MovimientoVisita movimientoVisita = movimientoVisitaRepository.findByFechaMovimientoAndInmuebleAndVisitanteAndEliminadoIsFalse(fechaDeMoviiento,inmueble,visitante);
            return movimientoVisita;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
