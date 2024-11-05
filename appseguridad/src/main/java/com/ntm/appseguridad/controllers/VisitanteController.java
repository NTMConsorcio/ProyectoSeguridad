package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Visitante;
import com.ntm.appseguridad.services.VisitanteServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/visitante")
public class VisitanteController extends BaseControllerImpl<Visitante, VisitanteServiceImpl> {
    public VisitanteController(VisitanteServiceImpl service) {super(service);}
}
