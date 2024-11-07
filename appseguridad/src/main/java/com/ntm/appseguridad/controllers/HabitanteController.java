package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.entities.Habitante;
import com.ntm.appseguridad.services.HabitanteServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/habitante")
public class HabitanteController extends BaseControllerImpl<Habitante, HabitanteServiceImpl>{
    public HabitanteController(HabitanteServiceImpl service) {super(service);}
}
