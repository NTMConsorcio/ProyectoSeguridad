package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.EmpleadoDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.rest.UnidadDeNegocioDAORest;
import com.ntm.clienteadministrativo.services.UnidadDeNegocioDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UnidadDeNegocioDTOService unidadServicio;

    @GetMapping("/nuevo")
    public String nuevoEmpleado(Model model) throws ErrorServiceException {
        List<UnidadDeNegocioDTO> unidades = unidadServicio.getActivos();
        model.addAttribute("tiposEmpleado", TipoEmpleado.values()); // Agrega los valores del enum al modelo
        model.addAttribute("unidades", unidades);
        model.addAttribute("empleado", new EmpleadoDTO());
        return viewEdit;
    }
}
