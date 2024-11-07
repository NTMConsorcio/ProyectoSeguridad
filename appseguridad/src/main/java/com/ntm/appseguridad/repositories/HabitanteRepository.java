package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Habitante;
import com.ntm.appseguridad.entities.Inmueble;

public interface HabitanteRepository extends BaseRepository<Habitante, String>{
    Habitante searchByNombreAndEliminadoFalse(String nombre);
    Habitante searchByInmuebleAndEliminadoFalse(Inmueble inmueble);
}
