package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.entities.PlanillaHoraria;
import com.ntm.appseguridad.services.PlanillaHorariaServiceImpl;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/planilla")
public class PlanillaHorariaController extends BaseControllerImpl<PlanillaHoraria, PlanillaHorariaServiceImpl>{
    public PlanillaHorariaController(PlanillaHorariaServiceImpl service) {super(service);}

    @GetMapping("/presente/{email}")
    public ResponseEntity<?> presentePorCorreo(@PathVariable("email") String email){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.darPresente(email));
        } catch (ErrorServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/salida/{email}")
    public ResponseEntity<?> salidaPorCorreo(@PathVariable("email") String email){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.darSalida(email));
        } catch (ErrorServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }
}
