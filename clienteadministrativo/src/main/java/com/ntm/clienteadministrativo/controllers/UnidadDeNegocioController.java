package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.services.DireccionDTOService;
import com.ntm.clienteadministrativo.services.ServicioDTOService;
import com.ntm.clienteadministrativo.services.UnidadDeNegocioDTOService;
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
@RequestMapping("/unidadDeNegocio")
public class UnidadDeNegocioController {
    private String viewList = "view/unidadDeNegocio/listUnidadDeNegocio";
    private String viewEdit = "view/unidadDeNegocio/editUnidadDeNegocio";

    @Autowired
    private UnidadDeNegocioDTOService service;
    @Autowired
    private ServicioDTOService servicioService;
    @Autowired
    private DireccionDTOService direccionService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<UnidadDeNegocioDTO> lista = service.getActivos();
            model.addAttribute("unidadesDeNegocio", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, UnidadDeNegocioDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("unidadDeNegocio", dto);
            cargarListas(model);
            return viewEdit;
        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistemas");
        }
        return viewList;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value = "id") String id, Model model) {
        try {
            service.eliminar(id);
            return "redirect:/unidadDeNegocio/list";
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
            UnidadDeNegocioDTO obj = service.buscar(id);
            model.addAttribute("unidadDeNegocio", obj);
            model.addAttribute("isDisabled", false);
            cargarListas(model);
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
            UnidadDeNegocioDTO obj = service.buscar(id);
            model.addAttribute("unidadDeNegocio", obj);
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
    public String aceptarEdit(Model model, UnidadDeNegocioDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
            } else {
                if (dto.getId() == null || dto.getId().isEmpty()) {
                    service.crear(dto.getServicio().getId(),dto.getDireccion().getId(),dto.getNombre());
                } else {
                    service.modificar(dto.getId(),dto.getServicio().getId(),dto.getDireccion().getId(),dto.getNombre());
                }

                return "redirect:/unidadDeNegocio/list";
            }
        } catch (ErrorServiceException ex) {
            ex.printStackTrace();
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
        }
        model.addAttribute("unidadDeNegocio", dto);
        cargarListas(model);
        return viewEdit;
    }

    public void cargarListas(Model model) throws ErrorServiceException {
        List<DireccionDTO> direccionesList = direccionService.listar();
        List<ServicioDTO> servicioDTOList = servicioService.listar();

        model.addAttribute("servicios", servicioDTOList);
        model.addAttribute("direcciones", direccionesList);
    }
}
