package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.enums.TipoEmpleado;

import java.util.List;

public interface EmpleadoRepository extends BaseRepository<Empleado, String>{
    public Empleado findByNombreAndEliminadoFalse(String nombre);
    public Empleado findByLegajoAndEliminadoFalse(String legajo);
    List<Empleado> findByTipoEmpleadoAndEliminadoFalse(TipoEmpleado tipoEmpleado);
}
