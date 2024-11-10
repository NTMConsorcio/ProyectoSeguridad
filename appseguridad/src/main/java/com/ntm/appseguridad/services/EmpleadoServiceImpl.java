package com.ntm.appseguridad.services;


import com.ntm.appseguridad.dto.EmpleadoDTO;
import com.ntm.appseguridad.entities.CacheLegajoSingleton;
import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.enums.TipoEmpleado;
import com.ntm.appseguridad.mappers.EmpleadoMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.EmpleadoRepository;
import com.ntm.appseguridad.repositories.UsuarioRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado,String> implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    public EmpleadoServiceImpl(BaseRepository<Empleado, String> baserepository, EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper, UsuarioRepository usuarioRepository) {
        super(baserepository);
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public Empleado searchByNombre(String nombre) throws Exception {
        try {
            Empleado empleado = empleadoRepository.findByNombreAndEliminadoFalse(nombre);
            return empleado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<Empleado> searchByTipo(TipoEmpleado tipo) throws Exception {
        try {
            List<Empleado> empleados = empleadoRepository.findByTipoEmpleadoAndEliminadoFalse(tipo);
            return empleados;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public EmpleadoDTO Crear(EmpleadoDTO empleadoDto) throws ErrorServiceException {
        try {
            Empleado empleado = empleadoMapper.toEntity(empleadoDto);
            CacheLegajoSingleton cache = CacheLegajoSingleton.getInstance();
            if (cache.getContadorLegajo() == 0) {
                cache.setContadorLegajo(Integer.parseInt(empleadoRepository.findFirstByOrderByLegajoDesc().getLegajo()) + 1);
            };
            empleado.setLegajo(String.valueOf(cache.getContadorLegajo()));
            Empleado empleadoGuardado = repository.save(empleado);
            cache.addContadorLegajo();

            return empleadoMapper.toDTO(empleadoGuardado);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error al persistir el empleado");
        }

    }


    @Override
    public <D> D convertToDto(Empleado entity) {
        return (D) empleadoMapper.toDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<Empleado> entities) {
        return (List<D>) empleadoMapper.toDtoList(entities);
    }

    @Override
    public boolean validar(Empleado entity, String caso) throws Exception {
        try {
            if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (entity.getApellido() == null || entity.getApellido().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el nombre");
            }

            if (entity.getContactos() == null || entity.getApellido().isEmpty()) {
                throw new ErrorServiceException("Debe indicar los contactos");
            }


            if (caso.equals("SAVE")) {
                if (empleadoRepository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El empleado ya existe en el sistema");
                }
                if (empleadoRepository.findByLegajoAndEliminadoFalse(entity.getLegajo()) != null) {
                    throw new ErrorServiceException("El empleado ya existe en el sistema");
                }
            } else {
                Optional<Empleado> ee = empleadoRepository.findByIdAndEliminadoFalse(entity.getId());
                if (ee.isPresent()) {
                    Empleado e = ee.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El empleado especificado no existe en el sistema");
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
