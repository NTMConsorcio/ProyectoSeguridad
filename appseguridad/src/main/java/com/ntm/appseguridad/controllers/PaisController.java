package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Pais;
import com.ntm.appseguridad.services.PaisServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/pais")
public class PaisController extends BaseControllerImpl<Pais, PaisServiceImpl>{
    public PaisController(PaisServiceImpl service) {super(service);}

}
