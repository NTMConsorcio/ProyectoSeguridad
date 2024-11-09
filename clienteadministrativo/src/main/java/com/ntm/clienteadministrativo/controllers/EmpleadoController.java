package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.EmpleadoDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.rest.UnidadDeNegocioDAORest;
import com.ntm.clienteadministrativo.services.EmpleadoDTOService;
import com.ntm.clienteadministrativo.services.UnidadDeNegocioDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    private String viewEdit = "view/empleado/editEmpleado";

    @Autowired
    EmpleadoDTOService empleadoService;

    @Autowired
    UnidadDeNegocioDTOService unidadServicio;

    @GetMapping("/edit")
    public String editEmpleado(Model model) throws ErrorServiceException {
        List<UnidadDeNegocioDTO> unidades = unidadServicio.getActivos();
        model.addAttribute("tiposEmpleado", TipoEmpleado.values()); // Agrega los valores del enum al modelo
        model.addAttribute("unidades", unidades);
        model.addAttribute("empleado", new EmpleadoDTO());
        return viewEdit;
    }

    @PostMapping("/nuevoEmpleado")
    public String nuevoEmpleado(ModelMap modelo, @RequestParam String documento, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String numero, @RequestParam String correo, @RequestParam TipoEmpleado tipoEmpleado, @RequestParam String idUnidadDeNegocio) throws ErrorServiceException {
        try {
            empleadoService.crear(documento, nombre, apellido, numero, correo, tipoEmpleado, idUnidadDeNegocio);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "view/index";
    }
}
