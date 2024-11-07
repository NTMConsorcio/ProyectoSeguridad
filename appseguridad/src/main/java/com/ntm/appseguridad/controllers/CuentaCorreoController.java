package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.services.CuentaCorreoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/cuentacorreo")
public class CuentaCorreoController extends BaseControllerImpl<CuentaCorreo, CuentaCorreoServiceImpl>{
    public CuentaCorreoController(CuentaCorreoServiceImpl service) {super(service);}

}
