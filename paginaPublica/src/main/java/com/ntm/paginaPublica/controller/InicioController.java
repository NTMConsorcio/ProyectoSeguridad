package com.ntm.paginaPublica.controller;

import com.ntm.paginaPublica.dto.EmpresaDTO;
import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.services.*;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private UnidadDeNegocioDTOService unidadDeNegocioDTOService;

    @Autowired
    private ServicioDTOService servicioDTOService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CuentaCorreoService serviceCorreo;

    @Autowired
    private EmpresaDTOService serviceEmpresa;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {

            authService.authenticateWithApi();

            List<ServicioDTO> lista1 = servicioDTOService.listar();
            List<UnidadDeNegocioDTO> lista2 = unidadDeNegocioDTOService.listar();
            HashMap<String, Integer> listaIndices = servicioDTOService.getMapIndices(lista1);
            HashMap<String, String> listaImagenes = servicioDTOService.obtenerImagenEnBase64(lista1);
            List<EmpresaDTO> listaEmpresa = serviceEmpresa.listar();
            EmpresaDTO empresa = listaEmpresa.getFirst();
            model.addAttribute("lista1", lista1);
            model.addAttribute("lista2", lista2);
            model.addAttribute("indices", listaIndices);
            model.addAttribute("imagenes", listaImagenes);
            model.addAttribute("empresa", empresa);
            return "index";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "index";
        }
    }

    @PostMapping("/send")
    public String send(@RequestParam("emailAddress") String email, Model model) {
        try {
            serviceCorreo.crear(email);
            return "redirect:https://sistemantm.site:8082/inicio";
        } catch (Exception e) {
            return "redirect:https://sistemantm.site:8082/inicio";
        }
    }

    @GetMapping("/")
    public String ini2() {
    return "redirect:https://sistemantm.site:8082/inicio";
    }
}
