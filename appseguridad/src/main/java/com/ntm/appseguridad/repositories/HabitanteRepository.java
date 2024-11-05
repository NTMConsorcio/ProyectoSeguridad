package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Habitante;

public interface HabitanteRepository extends BaseRepository<Habitante, String>{
    Habitante searchByNombreAndEliminadoFalse(String nombre);
}
