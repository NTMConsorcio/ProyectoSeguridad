package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.business.domain.UnidadDeNegocio;
import com.ntm.appseguridad.services.UnidadDeNegocioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/unidadDeNegocio")
public class UnidadDeNegocioController extends BaseControllerImpl<UnidadDeNegocio, UnidadDeNegocioServiceImpl>{
    public UnidadDeNegocioController(UnidadDeNegocioServiceImpl service) {super(service);}

    @GetMapping("/search")
    public ResponseEntity<?> searchPersona(@RequestParam String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.search(nombre));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }
}
