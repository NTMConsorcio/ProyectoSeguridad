package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import com.ntm.appseguridad.services.ContactoCorreoElectronicoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/contactoCorreoElectronico")
public class ContactoCorreoElectronicoController extends BaseControllerImpl<ContactoCorreoElectronico, ContactoCorreoElectronicoServiceImpl>{
    public ContactoCorreoElectronicoController(ContactoCorreoElectronicoServiceImpl service) {super(service);}

}
