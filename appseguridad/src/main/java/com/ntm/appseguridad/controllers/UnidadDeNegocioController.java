package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.dto.UnidadDeNegocioDTO;
import com.ntm.appseguridad.entities.UnidadDeNegocio;
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
            return ResponseEntity.status(HttpStatus.OK).body(service.searchByNombreContaining(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<UnidadDeNegocioDTO>> findAllDtoList() throws Exception {
        List<UnidadDeNegocioDTO> unidadesDeNegocio = service.findAllDtoList();
        return ResponseEntity.ok(unidadesDeNegocio);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UnidadDeNegocioDTO> findByIdDto(@PathVariable String id) throws Exception{
        System.out.println(id);
        UnidadDeNegocioDTO unidad = service.findByIdDto(id);
        System.out.println(unidad);
        return ResponseEntity.ok(unidad);
    }
}
