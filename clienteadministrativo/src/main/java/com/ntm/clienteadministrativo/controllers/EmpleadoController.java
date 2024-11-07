package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.EmpleadoDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    private String viewEdit = "view/empleado/editEmpleado";
    private final RestTemplate restTemplate;

    public EmpleadoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/nuevo")
    public String nuevoEmpleado(Model model) {
        List<UnidadDeNegocioDTO> unidades = restTemplate.getForObject(
                "http://localhost:9000/api/v1/unidadDeNegocio/activos",
                List.class
        );
        model.addAttribute("unidades", unidades);
        model.addAttribute("empleado", new EmpleadoDTO());
        return viewEdit;
    }
}
