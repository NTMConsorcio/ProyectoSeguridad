package com.ntm.appseguridad.services;


import com.ntm.appseguridad.dto.EmpleadoDTO;
import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.enums.TipoEmpleado;
import com.ntm.appseguridad.mappers.EmpleadoMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.EmpleadoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado,String> implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    public EmpleadoServiceImpl(BaseRepository<Empleado, String> baserepository, EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        super(baserepository);
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
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
    public EmpleadoDTO Crear(EmpleadoDTO empleadoDto) {
        Empleado empleado = empleadoMapper.toEntity(empleadoDto);
        empleado.setLegajo("1");
        System.out.println(empleado);
        Empleado empleadoGuardado = repository.save(empleado);
        System.out.println(empleadoGuardado);
        return empleadoMapper.toDTO(empleadoGuardado);
    }
    @Override
    public <D> D convertToDto(Empleado entity) {
        return null;
    }

    @Override
    public <D> List<D> convertToDtoList(List<Empleado> entities) {
        return List.of();
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
