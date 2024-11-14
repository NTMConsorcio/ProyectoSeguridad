package com.ntm.clienteadministrativo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    String inicioUrl = "view/index";

    @GetMapping("/inicio")
    public String inicio(Model model, Authentication authentication) throws Exception {
        try {
            // Obtener los roles del usuario
            if (authentication != null) {
                // Obtener los roles del usuario logeado
                boolean hasAdminRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
                boolean hasSuperAdminRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("SUPERADMIN"));
                boolean hasPersonalRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("PERSONAL"));
                boolean hasHabitanteRole = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("HABITANTE"));

                // Redirigir según el rol
                if (hasPersonalRole) {
                    return "redirect:view/empleado/indexEmpleado"; // Redirigir a la página de inicio del admin
                } else if (hasHabitanteRole) {
                    return "redirect:view/habitante/indexHabitante"; // Redirigir a la página de inicio del usuario normal
                } else{
                    return "redirect:view/admin/indexAdmin";
                }
            }

            // Si no tiene ningún rol, o algún otro tipo de usuario, puedes redirigir a una vista por defecto
            return "redirect:/default/inicio"; // Redirigir a página de inicio por defecto

        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el sistema");
            return "/view/categoria/categoriaList"; // Vista de error o lista por defecto
        }
    }


    @GetMapping("/")
    public String ini2(Model model) throws Exception {
        return "redirect:/inicio";
    }
}
