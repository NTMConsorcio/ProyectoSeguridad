package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;
import com.ntm.appseguridad.repositories.BaseRepository;

import java.util.List;

public interface PlanillaHorariaService extends BaseService<PlanillaHoraria, String> {
    List<PlanillaHoraria> ListByEmpleado(String empleado) throws Exception;
    List<PlanillaHoraria> ListByEstadoAndEmpleado(EstadoAsistencia estado, String empleado) throws Exception;
}
