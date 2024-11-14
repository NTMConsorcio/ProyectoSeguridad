package com.ntm.clienteadministrativo.controllers;


import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.dto.enums.EstadoAsistencia;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.services.EmpleadoDTOService;
import com.ntm.clienteadministrativo.services.PlanillaHorariaDTOService;
import com.ntm.clienteadministrativo.services.UnidadDeNegocioDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/planillaHoraria")
public class PlanillaHorariaController {
    private String viewEdit = "view/planillaHoraria/editPlanillaHoraria";
    private String viewList = "view/planillaHoraria/listPlanilla";

    @Autowired
    PlanillaHorariaDTOService planillaHorariaService;

    @Autowired
    EmpleadoDTOService empleadoServicio;

    @PostMapping("/presente")
    public String presente(@RequestParam("id") String correo, Model model, @RequestParam("origen") String origen) {
        try {
            planillaHorariaService.darPresenteYSalida(correo, "presente");
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            if ("listPlanilla".equals(origen)) {
                return viewList;
            } else if ("inicio".equals(origen)) {
                String error = URLEncoder.encode(ex.getMessage(), StandardCharsets.UTF_8);
                return "redirect:/inicio?error=" + error;
            }
        }
        if ("listPlanilla".equals(origen)) {
            return "redirect:/planillaHoraria/list";
        } else if ("inicio".equals(origen)) {
            return "redirect:/inicio";
        }
        return "redirect:/planillaHoraria/list";
    }

    @PostMapping("/salida")
    public String salida(@RequestParam("id") String correo, Model model, @RequestParam("origen") String origen) {
        try {
            planillaHorariaService.darPresenteYSalida(correo, "salida");

        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            if ("listPlanilla".equals(origen)) {
                return viewList;
            } else if ("inicio".equals(origen)) {
                String error = URLEncoder.encode(ex.getMessage(), StandardCharsets.UTF_8);
                return "redirect:/inicio?error=" + error;
            }
        }
        if ("listPlanilla".equals(origen)) {
            return "redirect:/planillaHoraria/list";
        } else if ("inicio".equals(origen)) {
            return "redirect:/inicio";
        }
        return "redirect:/planillaHoraria/list";
    }

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

    public void cargarLista(Model model) throws ErrorServiceException {
        try {
            List<PlanillaHorariaDTO> lista = planillaHorariaService.listar();
            lista.sort(Comparator.comparing(PlanillaHorariaDTO::getEntrada).reversed());
            model.addAttribute("condicionEspecial", false);
            model.addAttribute("planillasHorarias", lista);
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        }
    }

    @PostMapping("/aceptarEdicion")
    public String editAceptar(Model model, PlanillaHorariaDTO dto, BindingResult result, RedirectAttributes attributes, Authentication authentication) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
            } else {

                if (dto.getId() == null || dto.getId().isEmpty()) {
                    planillaHorariaService.crear(dto.getEntrada(), dto.getSalida(), dto.getEstadoAsistencia(), dto.getEmpleado().getId());
                } else {
                    planillaHorariaService.modificar(dto.getId(), dto.getEntrada(), dto.getSalida(), dto.getEstadoAsistencia(), dto.getEmpleado().getId());
                }
                return "redirect:/planillaHoraria/list";
            }
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("mensajeError", "Error en el formulario");
        }
        model.addAttribute("planilla", dto);
        cargarCombos(model, authentication);
        return viewEdit;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/baja")
    public String baja(@RequestParam(value = "id") String id, Model model) {
        try {
            planillaHorariaService.eliminar(id);
            return "redirect:/planillaHoraria/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error con el formulario");
        }
        return viewList;
    }

    @GetMapping("/nuevo")
    public String editPlanillaHoraria(Model model, Authentication authentication) throws ErrorServiceException {
        cargarCombos(model, authentication);
        PlanillaHorariaDTO planilla = new PlanillaHorariaDTO();
        model.addAttribute("planilla", new PlanillaHorariaDTO());
        // model.addAttribute("isEditMode", false);
        return viewEdit;
    }

    /*
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


     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/modificar")
    public String modificar(Model model, @RequestParam("id") String id, Authentication authentication) {
        try {
            PlanillaHorariaDTO obj = planillaHorariaService.buscar(id);
            //model.addAttribute("entrada", obj.getEntrada());
            //model.addAttribute("salida", obj.getSalida());
            model.addAttribute("planilla", obj);
            model.addAttribute("isDisabled", false);
            cargarCombos(model, authentication);
            // model.addAttribute("isEditMode", true);
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

    @GetMapping("/consultar")
    public String consultar(Model model, @RequestParam("id") String id, Authentication authentication) {
        try {
            PlanillaHorariaDTO obj = planillaHorariaService.buscar(id);
            model.addAttribute("planilla", obj);
            model.addAttribute("isDisabled", true);
            cargarCombos(model, authentication);
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

    public void cargarCombos(Model model, Authentication authentication) throws ErrorServiceException {
        List<EmpleadoDTO> empleados = empleadoServicio.listar();
        model.addAttribute("estadosAsistencia", EstadoAsistencia.values()); // Agrega los valores del enum al modelo
        model.addAttribute("empleados", empleados);
        boolean esAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("esAdmin", esAdmin);
    }

}
