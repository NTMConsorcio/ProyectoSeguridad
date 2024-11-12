package com.ntm.clienteadministrativo.controllers;


import com.ntm.clienteadministrativo.dto.DepartamentoDTO;
import com.ntm.clienteadministrativo.dto.InmuebleDTO;
import com.ntm.clienteadministrativo.dto.ProvinciaDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.enums.EstadoInmueble;
import com.ntm.clienteadministrativo.services.InmuebleDTOService;
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

import java.util.List;

@Controller
@RequestMapping("/inmueble")
public class InmuebleController {
    private String viewList = "view/inmueble/listInmueble";
    private String viewEdit = "view/inmueble/editInmueble";

    @Autowired
    private InmuebleDTOService service;

    @Autowired
    private UnidadDeNegocioDTOService unService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<InmuebleDTO> lista = service.listar();
            model.addAttribute("inmuebles", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, InmuebleDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("inmueble", dto);
            List<UnidadDeNegocioDTO> unList = unService.getActivos();

            /////////////////////////////////////////
            model.addAttribute("unidadesIn", unList);
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
            return "redirect:/inmueble/list";
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
            InmuebleDTO obj = service.buscar(id);
            model.addAttribute("inmueble", obj);
            model.addAttribute("isDisabled", false);
            List<UnidadDeNegocioDTO> unList = unService.getActivos();
            ///////////////////

            model.addAttribute("unidades_de_negocios", unList);
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
            InmuebleDTO obj = service.buscar(id);
            model.addAttribute("inmueble", obj);
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
    public String aceptarEdit(Model model, InmuebleDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("inmueble", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getNumeracion(),dto.getPiso(),dto.getPiso(), EstadoInmueble.OCUPADO,dto.getUnidadDeNegocio().getId());
            } else {
                service.modificar(dto.getId(), dto.getNumeracion(),dto.getPiso(),dto.getPiso(),dto.getEstadoInmueble(),dto.getUnidadDeNegocio().getId());
            }

            return "redirect:/inmueble/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("inmueble", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("inmueble", dto);
            return viewEdit;
        }
    }

}
