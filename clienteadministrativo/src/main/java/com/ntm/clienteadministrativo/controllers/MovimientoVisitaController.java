package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.EstadoMovimiento;
import com.ntm.clienteadministrativo.dto.enums.TipoMovilidad;
import com.ntm.clienteadministrativo.dto.enums.TipoMovimiento;
import com.ntm.clienteadministrativo.services.InmuebleDTOService;
import com.ntm.clienteadministrativo.services.MovimientoVisitaDTOService;
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
@RequestMapping("/movimientoVisita")
public class MovimientoVisitaController {
    private String viewList = "view/movimientoVisita/listmovimientoVisita";
    private String viewEdit = "view/movimientoVisita/editmovimientoVisita";

    @Autowired
    private MovimientoVisitaDTOService service;
    @Autowired
    private InmuebleDTOService inmuebleService;
    @Autowired
    private VisitanteDTOService visitanteService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<MovimientoVisitaDTO> lista = service.listar();
            model.addAttribute("movimientoVisita", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, MovimientoVisitaDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("movimientoVisita", dto);
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
            return "redirect:/movimientoVisita/list";
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
            MovimientoVisitaDTO obj = service.buscar(id);
            model.addAttribute("movimientoVisita", obj);
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
            MovimientoVisitaDTO obj = service.buscar(id);
            model.addAttribute("movimientoVisita", obj);
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
    public String aceptarEdit(Model model, MovimientoVisitaDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
            } else {
                if (dto.getId() == null || dto.getId().isEmpty()) {
                    service.crear(dto.getTipoMovilidad(),dto.getTipoMovimiento(),dto.getFechaMovimiento(),dto.getObservacion(),dto.getEstadoMovimiento(),dto.getDescripcionMovilidad(),dto.getInmueble().getId(),dto.getVisitante().getId());
                } else {
                    service.modificar(dto.getId(),dto.getTipoMovilidad(),dto.getTipoMovimiento(),dto.getFechaMovimiento(),dto.getObservacion(),dto.getEstadoMovimiento(),dto.getDescripcionMovilidad(),dto.getInmueble().getId(),dto.getVisitante().getId());
                }

                return "redirect:/movimientoVisita/list";
            }
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
        }
        model.addAttribute("movimientoVisita", dto);
        cargarListas(model);
        return viewEdit;
    }

    public void cargarListas(Model model) throws ErrorServiceException {
        List<InmuebleDTO> inmuebleDTOS = inmuebleService.listar();
        List<VisitanteDTO> visitanteDTOS = visitanteService.listar();
        model.addAttribute("tiposMovimiento", TipoMovimiento.values());
        model.addAttribute("estadosMovimiento", EstadoMovimiento.values());
        model.addAttribute("tiposMovilidad", TipoMovilidad.values());
        model.addAttribute("inmuebles", inmuebleDTOS);
        model.addAttribute("visitantes", visitanteDTOS);
    }
}
