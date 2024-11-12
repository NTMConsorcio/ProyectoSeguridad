package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;

import java.util.List;

public interface PlanillaHorariaRepository extends BaseRepository<PlanillaHoraria, String>{
    List<PlanillaHoraria> findByEmpleadoAndEliminadoFalse(Empleado empleado);
    List<PlanillaHoraria> findByEstadoAsistenciaAndEmpleadoAndEliminadoFalse(EstadoAsistencia estadoAsistencia, Empleado empleado);
    PlanillaHoraria findFirstByEmpleadoAndEliminadoFalseOrderByEntradaDesc(Empleado empleado);
}
