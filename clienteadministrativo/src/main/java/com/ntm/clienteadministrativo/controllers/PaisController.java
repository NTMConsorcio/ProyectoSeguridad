package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.services.PaisDTOService;
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
@RequestMapping("/pais")
public class PaisController {
    private String viewList = "view/pais/listPais";
    private String viewEdit = "view/pais/editPais";

    @Autowired
    private PaisDTOService service;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<PaisDTO> lista = service.listar();
            model.addAttribute("paises", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, PaisDTO dto) {
        model.addAttribute("isDisabled", false);
        model.addAttribute("pais", dto);
        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value="id") String id, Model model) {
        try {
            service.eliminar(id);
            return "redirect:/pais/list";
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
            PaisDTO obj = service.buscar(id);
            model.addAttribute("pais", obj);
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
            PaisDTO obj = service.buscar(id);
            model.addAttribute("pais", obj);
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
    public String aceptarEdit(Model model, PaisDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("pais", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getNombre());
            } else {
                service.modificar(dto.getId(), dto.getNombre());
            }

            return "redirect:/pais/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("pais", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("pais", dto);
            return viewEdit;
        }
    }
}
