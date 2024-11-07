package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Inmueble;

public interface InmuebleService extends BaseService<Inmueble, String>{
    Inmueble findByNumeracionAndPisoAndAndDptoAndEliminadoIsFalse(String nombre,String piso,String dpto) throws Exception;
}
