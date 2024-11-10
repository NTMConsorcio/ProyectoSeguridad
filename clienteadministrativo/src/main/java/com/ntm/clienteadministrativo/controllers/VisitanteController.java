package com.ntm.clienteadministrativo.controllers;


import com.ntm.clienteadministrativo.dto.VisitanteDTO;
import com.ntm.clienteadministrativo.services.VisitanteDTOService;
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

import java.util.List;

@Controller
@RequestMapping("/visitante")
public class VisitanteController {
    private String viewList = "view/visitante/listVisitante";
    private String viewEdit = "view/visitante/editVisitante";

    @Autowired
    private VisitanteDTOService service;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<VisitanteDTO> list = service.listar();
            model.addAttribute("visitantes", list);
        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }
    @GetMapping("/alta")
    public String alta(Model model, VisitanteDTO dto) {
        model.addAttribute("isDisabled", false);
        model.addAttribute("visitante", dto);
        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value="id") String id, Model model) {
        try {
            service.eliminar(id);
            return "redirect:/visitante/list";
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
            VisitanteDTO obj = service.buscar(id);
            model.addAttribute("visitante", obj);
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
            VisitanteDTO obj = service.buscar(id);
            model.addAttribute("visitante", obj);
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
    public String aceptarEdit(Model model, VisitanteDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("visitante", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getNombre(),dto.getApellido(),dto.getNumeroDeDocumento());
            } else {
                service.modificar(dto.getId(), dto.getNombre(),dto.getApellido(),dto.getNumeroDeDocumento());
            }

            return "redirect:/visitante/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("visitante", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("visitante", dto);
            return viewEdit;
        }
    }


}
