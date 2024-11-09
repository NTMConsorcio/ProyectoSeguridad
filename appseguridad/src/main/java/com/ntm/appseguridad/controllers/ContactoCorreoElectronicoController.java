package com.ntm.appseguridad.controllers;


import com.ntm.appseguridad.dto.ContactoCorreoElectronicoDTO;
import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import com.ntm.appseguridad.services.ContactoCorreoElectronicoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/contactoCorreoElectronico")
public class ContactoCorreoElectronicoController extends BaseControllerImpl<ContactoCorreoElectronico, ContactoCorreoElectronicoServiceImpl>{
    public ContactoCorreoElectronicoController(ContactoCorreoElectronicoServiceImpl service) {super(service);}

    @PostMapping("/crear")
    public ResponseEntity<?> crearContactoTelefonico (@RequestBody ContactoCorreoElectronicoDTO contactoDto){
        try{
            ContactoCorreoElectronicoDTO contactoNuevo = service.Crear(contactoDto);
            return ResponseEntity.status(HttpStatus.OK).body(contactoNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }
}
