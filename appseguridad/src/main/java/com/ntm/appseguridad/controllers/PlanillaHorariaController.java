package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.services.PlanillaHorariaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/planilla")
public class PlanillaHorariaController extends BaseControllerImpl<PlanillaHoraria, PlanillaHorariaServiceImpl>{
    public PlanillaHorariaController(PlanillaHorariaServiceImpl service) {super(service);}
}
