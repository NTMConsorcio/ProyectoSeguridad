package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.enums.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoRepository extends BaseRepository<Empleado, String>{
    public Empleado findByNombreAndEliminadoFalse(String nombre);
    public Empleado findByLegajoAndEliminadoFalse(String legajo);
    List<Empleado> findByTipoEmpleadoAndEliminadoFalse(TipoEmpleado tipoEmpleado);

    Empleado findFirstByOrderByLegajoDesc();

    @Query("SELECT e FROM Empleado e JOIN e.contactos c WHERE e.eliminado = FALSE AND c.email = :email")
    Empleado findEmpleadoByContacto(@Param("email") String email);
}
