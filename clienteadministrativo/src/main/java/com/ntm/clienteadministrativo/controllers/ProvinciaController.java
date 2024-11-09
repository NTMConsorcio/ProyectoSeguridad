package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.PaisDTO;
import com.ntm.clienteadministrativo.dto.ProvinciaDTO;
import com.ntm.clienteadministrativo.services.PaisDTOService;
import com.ntm.clienteadministrativo.services.ProvinciaDTOService;
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
@RequestMapping("/provincia")
public class ProvinciaController {
    private String viewList = "view/provincia/listProvincia";
    private String viewEdit = "view/provincia/editProvincia";
    private String redList = "redirect:/provincia/list";
    private String redEdit = "redirect:/provincia/alta";

    @Autowired
    private ProvinciaDTOService service;

    @Autowired
    private PaisDTOService paisService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<ProvinciaDTO> lista = service.listar();
            model.addAttribute("provincias", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, ProvinciaDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("provincia", dto);
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
            return "redirect:/provincia/list";
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
            ProvinciaDTO obj = service.buscar(id);
            model.addAttribute("provincia", obj);
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
            ProvinciaDTO obj = service.buscar(id);
            model.addAttribute("provincia", obj);
            model.addAttribute("isDisabled", true);
            return viewEdit;
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el sistema");
        }
        return viewList;
    }

    public void cargarListas(Model model) throws ErrorServiceException {
        List<PaisDTO> paisesList = paisService.listar();
        model.addAttribute("paises", paisesList);
    }

    @PostMapping("/aceptarEdit")
    public String aceptarEdit(Model model, ProvinciaDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
            } else {

                if (dto.getId() == null || dto.getId().isEmpty()) {
                    service.crear(dto.getNombre(), dto.getPais().getId());
                } else {
                    service.modificar(dto.getId(), dto.getNombre(), dto.getPais().getId());
                }

                return "redirect:/provincia/list";
            }
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
        }
        model.addAttribute("provincia", dto);
        cargarListas(model);
        return viewEdit;
    }
}
