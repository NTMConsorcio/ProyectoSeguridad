package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.ProvinciaDTO;
import com.ntm.clienteadministrativo.dto.DepartamentoDTO;
import com.ntm.clienteadministrativo.services.ProvinciaDTOService;
import com.ntm.clienteadministrativo.services.DepartamentoDTOService;
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
@RequestMapping("/departamento")
public class DepartamentoController {
    private String viewList = "view/departamento/listDepartamento";
    private String viewEdit = "view/departamento/editDepartamento";

    @Autowired
    private DepartamentoDTOService service;

    @Autowired
    private ProvinciaDTOService provinciaService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<DepartamentoDTO> lista = service.listar();
            model.addAttribute("departamentos", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, DepartamentoDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("departamento", dto);
            List<ProvinciaDTO> provinciasList = provinciaService.listar();
            model.addAttribute("provincias", provinciasList);
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
            return "redirect:/departamento/list";
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
            DepartamentoDTO obj = service.buscar(id);
            model.addAttribute("departamento", obj);
            model.addAttribute("isDisabled", false);
            List<ProvinciaDTO> provinciasList = provinciaService.listar();
            model.addAttribute("provincias", provinciasList);
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
            DepartamentoDTO obj = service.buscar(id);
            model.addAttribute("departamento", obj);
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
    public String aceptarEdit(Model model, DepartamentoDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("departamento", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getNombre(), dto.getProvincia().getId());
            } else {
                service.modificar(dto.getId(), dto.getNombre(), dto.getProvincia().getId());
            }

            return "redirect:/departamento/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("departamento", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("departamento", dto);
            return viewEdit;
        }
    }
}
