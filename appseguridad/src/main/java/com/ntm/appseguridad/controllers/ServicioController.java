package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Servicio;
import com.ntm.appseguridad.services.ServicioServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/servicio")
public class ServicioController extends BaseControllerImpl<Servicio, ServicioServiceImpl>{
    public ServicioController(ServicioServiceImpl service) {super(service);}

}
