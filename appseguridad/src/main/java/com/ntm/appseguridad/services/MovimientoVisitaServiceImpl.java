package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.*;
import com.ntm.appseguridad.mappers.MovimientoVisitaMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.MovimientoVisitaRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoVisitaServiceImpl extends BaseServiceImpl<MovimientoVisita,String> implements MovimientoVisitaService {
    private final MovimientoVisitaRepository movimientoVisitaRepository;
    private final MovimientoVisitaMapper movimientoVisitaMapper;

    @Autowired
    private HabitanteServiceImpl habitanteService;

    public MovimientoVisitaServiceImpl(BaseRepository<MovimientoVisita,String> baseRepository, MovimientoVisitaRepository movimientoVisitaRepository, MovimientoVisitaMapper mapper) {
        super(baseRepository);
        this.movimientoVisitaRepository = movimientoVisitaRepository;
        this.movimientoVisitaMapper = mapper;
    }


    @Override
    public <D> D convertToDto(MovimientoVisita entity) {
        return (D) movimientoVisitaMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<MovimientoVisita> entities) {
        return (List<D>) movimientoVisitaMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(MovimientoVisita entity, String caso) throws Exception {
        try {
            if (entity.getFechaMovimiento() == null) {
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

    @Override
    @Transactional
    public MovimientoVisita save(MovimientoVisita entity) throws Exception {
        try {

            Habitante habitante = habitanteService.getHabitanteByInmueble(entity.getInmueble());

            System.out.println(habitante.getContactos());
            /*
            validar(entity, "SAVE");
            entity = repository.save(entity);
            return entity;

             */
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al guardar la entidad");
        }
    }

    public void darAvisoMovimiento(MovimientoVisita visita) throws ErrorServiceException {
        try {
            Habitante habitante = habitanteService.getHabitanteByInmueble(visita.getInmueble());

            System.out.println(habitante.getContactos());
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error enviando el mail");
        }
    }
}
