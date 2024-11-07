package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.entities.MovimientoVisita;
import com.ntm.appseguridad.entities.Visitante;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MovimientoVisitaRepository extends BaseRepository<MovimientoVisita, String>{
    MovimientoVisita findByFechaMovimientoAndInmuebleAndVisitanteAndEliminadoIsFalse(Date fechaDeMoviiento, Inmueble inmueble, Visitante visitante);

}
