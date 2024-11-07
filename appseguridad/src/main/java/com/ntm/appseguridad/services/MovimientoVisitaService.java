package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.entities.MovimientoVisita;
import com.ntm.appseguridad.entities.Visitante;

import java.util.Date;

public interface MovimientoVisitaService extends BaseService<MovimientoVisita, String>{
    MovimientoVisita  findByFechaMovimientoAndInmuebleAndVisitanteAndEliminadoIsFalse(Date fechaDeMoviiento, Inmueble inmueble, Visitante visitante) throws Exception;
}
