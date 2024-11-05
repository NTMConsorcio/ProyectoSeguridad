package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Inmueble;
import com.ntm.appseguridad.services.InmuebleServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/inmueble")
public class InmuebleController extends BaseControllerImpl<Inmueble, InmuebleServiceImpl> {
    public InmuebleController(InmuebleServiceImpl service) {super(service);}
}
