package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.entities.enums.TipoEmpleado;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.EmpleadoRepository;

import java.util.List;

public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado,String> implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(BaseRepository<Empleado, String> baserepository, EmpleadoRepository empleadoRepository) {super(baserepository);
        this.empleadoRepository = empleadoRepository;
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
}
