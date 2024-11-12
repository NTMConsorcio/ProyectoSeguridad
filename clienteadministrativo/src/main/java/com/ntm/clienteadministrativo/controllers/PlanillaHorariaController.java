package com.ntm.clienteadministrativo.controllers;


import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.EstadoAsistencia;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.services.EmpleadoDTOService;
import com.ntm.clienteadministrativo.services.PlanillaHorariaDTOService;
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

import java.util.List;

@Controller
@RequestMapping("/planillaHoraria")
public class PlanillaHorariaController {
    private String viewEdit = "view/planillaHoraria/editEmpleado";
    private String viewList = "view/planillaHoraria/listEmpleado";

    @Autowired
    PlanillaHorariaDTOService planillaHorariaService;

    @Autowired
    EmpleadoDTOService empleadoServicio;


    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<PlanillaHorariaDTO> lista = planillaHorariaService.listar();
            model.addAttribute("planillasHorarias", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value = "id") String id, Model model) {
        try {
            planillaHorariaService.eliminar(id);
            return "redirect:/empleado/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error con el formulario");
        }
        return viewList;
    }

    @GetMapping("/nuevo")
    public String editPlanillaHoraria(Model model) throws ErrorServiceException {
        cargarCombos(model);
        model.addAttribute("planillaHoraria", new PlanillaHorariaDTO());
        model.addAttribute("isEditMode", false);
        return viewEdit;
    }

    @PostMapping("/aceptarEdit")
    public String edit(ModelMap modelo, PlanillaHorariaDTO dto, @RequestParam String idEmpleado) throws ErrorServiceException {
        try {
            if (dto.getId() == null || dto.getId().isEmpty()) {
                planillaHorariaService.crear(dto.getEntrada(),dto.getSalida(),dto.getEstadoAsistencia(),dto.getEmpleado().getId());
            } else {
                planillaHorariaService.modificar(dto.getId(), dto.getEntrada(),dto.getSalida(),dto.getEstadoAsistencia(),idEmpleado);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "view/index";
    }

    @GetMapping("/modificar")
    public String modificar(Model model, @RequestParam("id") String id) {
        try {
            PlanillaHorariaDTO obj = planillaHorariaService.buscar(id);
            model.addAttribute("entrada", obj.getEntrada());
            model.addAttribute("salida", obj.getSalida());
            model.addAttribute("isDisabled", false);
            cargarCombos(model);
            model.addAttribute("isEditMode", true);
            return viewEdit;
        } catch (ErrorServiceException ex) {
            ex.printStackTrace();
            model.addAttribute("mensajeError", ex.getMessage());
            return viewList;
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("mensajeError", "Error en el sistema");
            return viewList;
        }
    }

    public void cargarCombos(Model model) throws ErrorServiceException {
        List<EmpleadoDTO> empleados = empleadoServicio.listar();
        model.addAttribute("estadosAsistencia", EstadoAsistencia.values()); // Agrega los valores del enum al modelo
        model.addAttribute("empleados", empleados);
    }

}
