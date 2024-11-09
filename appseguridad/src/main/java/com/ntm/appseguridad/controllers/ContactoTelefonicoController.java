package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.dto.ContactoTelefonicoDTO;
import com.ntm.appseguridad.entities.ContactoTelefonico;
import com.ntm.appseguridad.services.ContactoTelefonicoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/contactoTelefonico")
public class ContactoTelefonicoController extends BaseControllerImpl<ContactoTelefonico, ContactoTelefonicoServiceImpl>{
    public ContactoTelefonicoController(ContactoTelefonicoServiceImpl service) {super(service);}

    @PostMapping("/crear")
    public ResponseEntity<?> crearContactoTelefonico (@RequestBody ContactoTelefonicoDTO contactoDto){
        try{
            ContactoTelefonicoDTO contactoNuevo = service.Crear(contactoDto);
            return ResponseEntity.status(HttpStatus.OK).body(contactoNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }

}
