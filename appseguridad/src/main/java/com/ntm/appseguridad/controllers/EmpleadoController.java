package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.dto.EmpleadoDTO;
import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.services.EmpleadoServiceImpl;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoServiceImpl>{
    public EmpleadoController(EmpleadoServiceImpl service) {super(service);}
    @PostMapping("/crear")
    public ResponseEntity<?> crear (@RequestBody EmpleadoDTO empleadoDto){
        try{
            EmpleadoDTO empleadoNuevo = service.Crear(empleadoDto);
            return ResponseEntity.status(HttpStatus.OK).body(empleadoNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }

}

