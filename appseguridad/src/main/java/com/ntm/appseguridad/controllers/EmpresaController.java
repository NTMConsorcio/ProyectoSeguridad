package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Empresa;
import com.ntm.appseguridad.services.EmpresaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/empresa")
public class EmpresaController extends BaseControllerImpl<Empresa, EmpresaServiceImpl>{
    public EmpresaController(EmpresaServiceImpl service) {super(service);}

    @GetMapping("/search")
    public ResponseEntity<?> searchEmpresa(@RequestParam String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.search(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }
}
