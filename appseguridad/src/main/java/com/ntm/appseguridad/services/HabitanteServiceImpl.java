package com.ntm.appseguridad.services;


import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.Habitante;
import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.HabitanteRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HabitanteServiceImpl extends BaseServiceImpl<Habitante,String> implements HabitanteService {

    private final HabitanteRepository habitanteRepository;

    public HabitanteServiceImpl(BaseRepository<Habitante, String> baserepository, HabitanteRepository habitanteRepository) {super(baserepository);
        this.habitanteRepository = habitanteRepository;
    }

    @Override
    public Habitante searchByNombre(String nombre) throws Exception {
        try {
            Habitante habitante = habitanteRepository.searchByNombreAndEliminadoFalse(nombre);
            return habitante;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean validar(Habitante entity, String caso) throws Exception {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (entity.getApellido() == null || entity.getApellido().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el apellido");
            }

            if (entity.getContactos() == null || entity.getApellido().isEmpty()) {
                throw new ErrorServiceException("Debe indicar los contactos");
            }

            if (entity.getInmueble() == null) {
                throw new ErrorServiceException("Debe indicar el inmueble");
            }

            if (habitanteRepository.searchByInmuebleAndEliminadoFalse(entity.getInmueble()) != null) {
                throw new ErrorServiceException("El inmueble ya tiene un habitante asociado");
            }

            if (caso.equals("SAVE")) {
                if (habitanteRepository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El habitante ya existe en el sistema");
                }
            } else {
                Optional<Habitante> ha = habitanteRepository.findByIdAndEliminadoFalse(entity.getId());
                if (ha.isPresent()) {
                    Habitante h = ha.get();
                    if (!h.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El habitante especificado no existe en el sistema");
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
