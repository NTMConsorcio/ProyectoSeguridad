package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Direccion;
import com.ntm.appseguridad.services.DireccionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/direccion")
public class DireccionController extends BaseControllerImpl<Direccion, DireccionServiceImpl>{
    public DireccionController(DireccionServiceImpl service) {super(service);}

}
