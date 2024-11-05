package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Habitante;

public interface HabitanteService extends BaseService<Habitante, String>{
    Habitante searchByNombre(String nombre) throws Exception;
}
