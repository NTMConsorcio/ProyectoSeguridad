package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.ContactoTelefonico;
import com.ntm.appseguridad.services.ContactoTelefonicoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/contactoTelefonico")
public class ContactoTelefonicoController extends BaseControllerImpl<ContactoTelefonico, ContactoTelefonicoServiceImpl>{
    public ContactoTelefonicoController(ContactoTelefonicoServiceImpl service) {super(service);}

}
