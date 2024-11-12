package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.dto.HabitanteDTO;
import com.ntm.appseguridad.entities.Habitante;
import com.ntm.appseguridad.services.HabitanteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/habitante")
public class HabitanteController extends BaseControllerImpl<Habitante, HabitanteServiceImpl>{
    public HabitanteController(HabitanteServiceImpl service) {super(service);}

    @PostMapping("/crear")
    public ResponseEntity<?> crear (@RequestBody HabitanteDTO habitanteDto){
        try{
            HabitanteDTO habitanteNuevo = service.Crear(habitanteDto);
            return ResponseEntity.status(HttpStatus.OK).body(habitanteNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }
}
