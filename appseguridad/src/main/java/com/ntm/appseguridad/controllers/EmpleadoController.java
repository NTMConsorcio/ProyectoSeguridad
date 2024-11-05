package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.entities.Empleado;
import com.ntm.appseguridad.services.EmpleadoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoServiceImpl>{
    public EmpleadoController(EmpleadoServiceImpl service) {super(service);}
}

