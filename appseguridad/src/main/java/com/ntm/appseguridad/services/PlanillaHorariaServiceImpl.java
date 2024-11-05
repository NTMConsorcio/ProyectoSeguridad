package com.ntm.appseguridad.services;



import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.PlanillaHorariaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PlanillaHorariaServiceImpl extends BaseServiceImpl<PlanillaHoraria,String> implements PlanillaHorariaService {

    private final PlanillaHorariaRepository planillaHorariaRepository;

    public PlanillaHorariaServiceImpl(BaseRepository<PlanillaHoraria, String> baserepository, PlanillaHorariaRepository planillaHorariaRepository) {super(baserepository);
        this.planillaHorariaRepository = planillaHorariaRepository;
    }


    @Override
    public List<PlanillaHoraria> ListByEmpleado(String empleado) throws Exception {
        try {
            List<PlanillaHoraria> planillas = planillaHorariaRepository.findByEmpleadoAndEliminadoFalse(empleado);
            return planillas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<PlanillaHoraria> ListByEstadoAndEmpleado(EstadoAsistencia estado, String empleado) throws Exception {
        try {
            List<PlanillaHoraria> planillas = planillaHorariaRepository.findByEstadoAsistenciaAndEmpleadoAndEliminadoFalse(estado, empleado);
            return planillas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
