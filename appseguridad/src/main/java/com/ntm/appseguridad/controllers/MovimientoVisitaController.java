package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.MovimientoVisita;
import com.ntm.appseguridad.services.MovimientoVisitaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/movimientoVisita")
public class MovimientoVisitaController extends BaseControllerImpl<MovimientoVisita, MovimientoVisitaServiceImpl> {
    public MovimientoVisitaController(MovimientoVisitaServiceImpl service) {super(service);}
}
