package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;

import java.util.List;

public interface PlanillaHorariaRepository extends BaseRepository<PlanillaHoraria, String>{
    List<PlanillaHoraria> findByEmpleadoAndEliminadoFalse(String empleado);
    List<PlanillaHoraria> findByEstadoAsistenciaAndEmpleadoAndEliminadoFalse(EstadoAsistencia estadoAsistencia, String empleado);
}
