package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.DepartamentoDTO;
import com.ntm.clienteadministrativo.dto.LocalidadDTO;
import com.ntm.clienteadministrativo.services.DepartamentoDTOService;
import com.ntm.clienteadministrativo.services.LocalidadDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/localidad")
public class LocalidadController {
    private String viewList = "view/localidad/listLocalidad";
    private String viewEdit = "view/localidad/editLocalidad";

    @Autowired
    private LocalidadDTOService service;

    @Autowired
    private DepartamentoDTOService departamentoService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<LocalidadDTO> lista = service.listar();
            model.addAttribute("localidades", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, LocalidadDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("localidad", dto);
            List<DepartamentoDTO> departamentosList = departamentoService.listar();
            return viewEdit;
        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistemas");
        }
        return viewList;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value="id") String id, Model model) {
        try {
            service.eliminar(id);
            return "redirect:/localidad/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error con el formulario");
        }
        return viewList;
    }

    @GetMapping("/modificar")
    public String modificar(Model model, @RequestParam("id") String id) {
        try {
            LocalidadDTO obj = service.buscar(id);
            model.addAttribute("localidad", obj);
            model.addAttribute("isDisabled", false);
            return viewEdit;
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            return viewList;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el sistema");
            return viewList;
        }
    }

    @GetMapping("/consultar")
    public String consultar(Model model, @RequestParam("id") String id) {
        try {
            LocalidadDTO obj = service.buscar(id);
            model.addAttribute("localidad", obj);
            model.addAttribute("isDisabled", true);
            return viewEdit;
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el sistema");
        }
        return viewList;
    }

    @PostMapping("/aceptarEdit")
    public String aceptarEdit(Model model, LocalidadDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("localidad", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getNombre(), dto.getDepartamento().getId());
            } else {
                service.modificar(dto.getId(), dto.getNombre(), dto.getDepartamento().getId());
            }

            return "redirect:/localidad/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("localidad", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("localidad", dto);
            return viewEdit;
        }
    }
}
