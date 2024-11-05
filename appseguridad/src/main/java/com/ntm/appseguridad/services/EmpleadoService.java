package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.enums.TipoEmpleado;

import java.util.List;

public interface EmpleadoService extends BaseService<Empleado, String>{
    Empleado searchByNombre(String nombre) throws Exception;
    List<Empleado> searchByTipo(TipoEmpleado tipo) throws Exception;
}
