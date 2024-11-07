package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Departamento;
import com.ntm.appseguridad.entities.Pais;
import com.ntm.appseguridad.services.DepartamentoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/departamento")
public class DepartamentoController extends BaseControllerImpl<Departamento, DepartamentoServiceImpl>{
    public DepartamentoController(DepartamentoServiceImpl service) {super(service);}

}
