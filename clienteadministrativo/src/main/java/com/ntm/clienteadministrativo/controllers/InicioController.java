package com.ntm.clienteadministrativo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    String inicioUrl = "view/index";

    @GetMapping("/inicio")
    public String inicio(Model model) throws Exception {
        try {
            return inicioUrl;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el sistema");
            return "/view/categoria/categoriaList";
        }
    }

    @GetMapping("/tables")
    public String ini2(Model model) throws Exception {
        try {
            return  "view/tables";
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el sistema");
            return "view/categoria/categoriaList";
        }
    }
}
