package com.ntm.appseguridad.services;



import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.PlanillaHorariaRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PlanillaHorariaServiceImpl extends BaseServiceImpl<PlanillaHoraria,String> implements PlanillaHorariaService {

    private final PlanillaHorariaRepository planillaHorariaRepository;

    public PlanillaHorariaServiceImpl(BaseRepository<PlanillaHoraria, String> baserepository, PlanillaHorariaRepository planillaHorariaRepository) {super(baserepository);
        this.planillaHorariaRepository = planillaHorariaRepository;
    }


    @Override
    public List<PlanillaHoraria> ListByEmpleado(Empleado empleado) throws Exception {
        try {
            List<PlanillaHoraria> planillas = planillaHorariaRepository.findByEmpleadoAndEliminadoFalse(empleado);
            return planillas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<PlanillaHoraria> ListByEstadoAndEmpleado(EstadoAsistencia estado, Empleado empleado) throws Exception {
        try {
            List<PlanillaHoraria> planillas = planillaHorariaRepository.findByEstadoAsistenciaAndEmpleadoAndEliminadoFalse(estado, empleado);
            return planillas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public <D> List<D> convertToDtoList(List<PlanillaHoraria> entities) {
        return List.of();
    }

    @Override
    public boolean validar(PlanillaHoraria entity, String caso) throws Exception {
        try {
            if (entity.getEmpleado() == null || entity.getEmpleado().getId().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el empleado");
            }

            if (entity.getEntrada() == null) {
                throw new ErrorServiceException("Debe indicar la entrada");
            }

            if (entity.getEstadoAsistencia() == null){
                throw new ErrorServiceException("Debe indicar el estado de la asistencia");
            }

            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("La asistencia ya existe en el sistema");
                }
            } else {
                Optional<PlanillaHoraria> pp = repository.findByIdAndEliminadoFalse(entity.getId());
                if (pp.isPresent()) {
                    PlanillaHoraria p = pp.get();
                    if (!p.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("La planilla especificado no existe en el sistema");
                    }
                }
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }
    }
}
