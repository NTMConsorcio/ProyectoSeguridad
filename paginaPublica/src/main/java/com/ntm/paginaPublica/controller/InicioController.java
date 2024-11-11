package com.ntm.paginaPublica.controller;

import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.services.AuthService;
import com.ntm.paginaPublica.services.ServicioDTOService;
import com.ntm.paginaPublica.services.UnidadDeNegocioDTOService;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private UnidadDeNegocioDTOService unidadDeNegocioDTOService;

    @Autowired
    private ServicioDTOService servicioDTOService;

    @Autowired
    private AuthService authService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {

            authService.authenticateWithApi();

            List<ServicioDTO> lista1 = servicioDTOService.listar();
            List<UnidadDeNegocioDTO> lista2 = unidadDeNegocioDTOService.listar();
            model.addAttribute("lista1", lista1);
            model.addAttribute("lista2", lista2);
            return "index";
        } catch (ErrorServiceException ex) {
            return "error";
        }
    }
}
