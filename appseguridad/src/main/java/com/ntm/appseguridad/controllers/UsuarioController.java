package com.ntm.appseguridad.controllers;
import com.ntm.appseguridad.dto.UsuarioDTO;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.services.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl>{
    public UsuarioController(UsuarioServiceImpl service) {super(service);}

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario (@RequestBody UsuarioDTO usuarioDto){
        try{
            UsuarioDTO usuarioNuevo = service.Crear(usuarioDto);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> getOne(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.convertToDto(service.searchByCuenta(nombre)));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/buscar/per/{idUsuario}")
    public ResponseEntity<?> getPorPersonaId(@PathVariable String idUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.convertToDto(service.searchByIdPersona(idUsuario)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
