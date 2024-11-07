package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Localidad;
import com.ntm.appseguridad.services.LocalidadServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/localidad")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl>{
    public LocalidadController(LocalidadServiceImpl service) {super(service);}

}
