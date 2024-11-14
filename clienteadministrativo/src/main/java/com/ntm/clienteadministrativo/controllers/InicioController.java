package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
public class InicioController {
    String inicioUrl = "view/index";

    @Autowired
    private MovimientoVisitaController movimientoVisitaController;

    @Autowired
    private PlanillaHorariaController planillaHorariaController;

    @Autowired
    private EmpleadoController empleadoController;

    @GetMapping("/inicio")
    public String inicio(Model model, Authentication authentication, @RequestParam(name="error", required = false) String error) throws Exception {
        try {
            if (error != null) {
                error = URLDecoder.decode(error, StandardCharsets.UTF_8);
                model.addAttribute("mensajeError", error);
            }
            if (authentication != null) {

                boolean hasAdminRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
                boolean hasSuperAdminRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_SUPERADMIN"));
                boolean hasPersonalRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_PERSONAL"));
                boolean hasHabitanteRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_HABITANTE"));
                if (hasPersonalRole) {
                    movimientoVisitaController.cargarLista(model);
                    planillaHorariaController.cargarLista(model);
                    model.addAttribute("condicionEspecial", true);
                    return "view/empleado/indexPersonal";
                } else if (hasHabitanteRole) {
                    movimientoVisitaController.cargarLista(model);
                    return "view/habitante/indexHabitante";
                } else {
                    empleadoController.cargarLista(model);
                    return "view/indexAdmin";
                }
            } else {
                throw new ErrorServiceException("El usuario no se encuentra logueado");
            }
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            return "view/movimientoVisita/list";
        }
    }


    @GetMapping("/")
    public String ini2(Model model) throws Exception {
        return "redirect:/inicio";
    }
}
