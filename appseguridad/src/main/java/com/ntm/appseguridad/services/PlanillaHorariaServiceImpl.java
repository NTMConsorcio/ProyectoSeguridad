package com.ntm.appseguridad.services;



import com.ntm.appseguridad.dto.HabitanteDTO;
import com.ntm.appseguridad.dto.PlanillaHorariaDTO;
import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.Habitante;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.entities.enums.EstadoAsistencia;
import com.ntm.appseguridad.mappers.ContactoCorreoElectronicoMapper;
import com.ntm.appseguridad.mappers.PlanillaHorariaMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.PlanillaHorariaRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlanillaHorariaServiceImpl extends BaseServiceImpl<PlanillaHoraria,String> implements PlanillaHorariaService {

    private final PlanillaHorariaRepository planillaHorariaRepository;

    private final PlanillaHorariaMapper planillaHorariaMapper;

    @Autowired
    private EmpleadoServiceImpl empleadoService;

    public PlanillaHorariaServiceImpl(BaseRepository<PlanillaHoraria, String> baserepository, PlanillaHorariaRepository planillaHorariaRepository, PlanillaHorariaMapper planillaHorariaMapper) {super(baserepository);
        this.planillaHorariaRepository = planillaHorariaRepository;
        this.planillaHorariaMapper = planillaHorariaMapper;
    }

    public PlanillaHorariaDTO Crear(PlanillaHorariaDTO planillaDTO) throws ErrorServiceException {
        try {

            PlanillaHoraria planillaHoraria = planillaHorariaMapper.toEntity(planillaDTO);
            PlanillaHoraria planillaHorariaGuardado = repository.save(planillaHoraria);
            return planillaHorariaMapper.toDTO(planillaHorariaGuardado);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error al persistir el habitante");
        }

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
    public <D> D convertToDto(PlanillaHoraria entity) {
        return (D) planillaHorariaMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<PlanillaHoraria> entities) {
        return (List<D>) planillaHorariaMapper.toDtoList(entities);
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

    public PlanillaHorariaDTO darPresente(String correo) throws ErrorServiceException {
        try {
            Empleado empleado = empleadoService.findByCorreo(correo);
            if (empleado == null) {
                throw new ErrorServiceException("Error, el usuario no es empleado");
            }
            PlanillaHoraria aux = planillaHorariaRepository.findFirstByEmpleadoAndEliminadoFalseOrderByEntradaDesc(empleado);
            if (aux != null) {
                if (aux.getSalida() == null) {
                    throw new ErrorServiceException("La última salida nunca se completó, completela y vuelva a intentar");
                }
            }
            PlanillaHoraria plan = new PlanillaHoraria();
            plan.setEmpleado(empleado);
            plan.setEstadoAsistencia(EstadoAsistencia.PRESENTE);
            plan.setEliminado(false);
            plan.setEntrada(new Date());
            repository.save(plan);
            return planillaHorariaMapper.toDTO(plan);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error. Posiblemente el usuario no es un empleado");
        }
    }

    public PlanillaHorariaDTO darSalida(String correo) throws ErrorServiceException {
        try {
            Empleado empleado = empleadoService.findByCorreo(correo);
            if (empleado == null) {
                throw new ErrorServiceException("Error, el usuario no es empleado");
            }
            PlanillaHoraria aux = planillaHorariaRepository.findFirstByEmpleadoAndEliminadoFalseOrderByEntradaDesc(empleado);
            if (aux != null) {
                if (aux.getSalida() != null) {
                    throw new ErrorServiceException("No se encontró una entrada incompleta.");
                }
            }
            aux.setSalida(new Date());
            repository.save(aux);
            return planillaHorariaMapper.toDTO(aux);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error. Posiblemente el usuario no es un empleado");
        }
    }
}
