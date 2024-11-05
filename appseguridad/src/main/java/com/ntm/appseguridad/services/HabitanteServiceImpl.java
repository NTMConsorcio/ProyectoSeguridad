package com.ntm.appseguridad.services;


import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.Habitante;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.HabitanteRepository;


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
}
