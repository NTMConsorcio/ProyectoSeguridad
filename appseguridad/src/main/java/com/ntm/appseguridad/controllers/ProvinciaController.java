package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.services.ProvinciaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/provincia")
public class ProvinciaController extends BaseControllerImpl<Provincia, ProvinciaServiceImpl>{
    public ProvinciaController(ProvinciaServiceImpl service) {super(service);}

}
