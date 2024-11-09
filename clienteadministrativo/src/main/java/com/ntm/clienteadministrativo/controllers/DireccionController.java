package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.LocalidadDTO;
import com.ntm.clienteadministrativo.dto.DireccionDTO;
import com.ntm.clienteadministrativo.services.LocalidadDTOService;
import com.ntm.clienteadministrativo.services.DireccionDTOService;
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
@RequestMapping("/direccion")
public class DireccionController {
    private String viewList = "view/direccion/listDireccion";
    private String viewEdit = "view/direccion/editDireccion";

    @Autowired
    private DireccionDTOService service;

    @Autowired
    private LocalidadDTOService localidadService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<DireccionDTO> lista = service.listar();
            model.addAttribute("direcciones", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, DireccionDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("direccion", dto);
            List<LocalidadDTO> localidadesList = localidadService.listar();
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
            return "redirect:/direccion/list";
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
            DireccionDTO obj = service.buscar(id);
            model.addAttribute("direccion", obj);
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
            DireccionDTO obj = service.buscar(id);
            model.addAttribute("direccion", obj);
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
    public String aceptarEdit(Model model, DireccionDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("direccion", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getCalle(), dto.getNumeracion(), dto.getLatitud(), dto.getLongitud(), dto.getLocalidad().getId());
            } else {
                service.modificar(dto.getId(), dto.getCalle(), dto.getNumeracion(), dto.getLatitud(), dto.getLongitud(), dto.getLocalidad().getId());
            }

            return "redirect:/direccion/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("direccion", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("direccion", dto);
            return viewEdit;
        }
    }
}
