package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.EmpresaDTO;
import com.ntm.clienteadministrativo.dto.CuentaCorreoDTO;
import com.ntm.clienteadministrativo.services.EmpresaDTOService;
import com.ntm.clienteadministrativo.services.CuentaCorreoDTOService;
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
@RequestMapping("/cuentaCorreo")
public class CuentaCorreoController {
    private String viewList = "view/cuentaCorreo/listCuentaCorreo";
    private String viewEdit = "view/cuentaCorreo/editCuentaCorreo";

    @Autowired
    private CuentaCorreoDTOService service;

    @Autowired
    private EmpresaDTOService empresaService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<CuentaCorreoDTO> lista = service.listar();
            model.addAttribute("cuentasCorreo", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, CuentaCorreoDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("cuentaCorreo", dto);
            List<EmpresaDTO> empresasList = empresaService.listar();
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
            return "redirect:/cuentaCorreo/list";
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
            CuentaCorreoDTO obj = service.buscar(id);
            model.addAttribute("cuentaCorreo", obj);
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
            CuentaCorreoDTO obj = service.buscar(id);
            model.addAttribute("cuentaCorreo", obj);
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
    public String aceptarEdit(Model model, CuentaCorreoDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("cuentaCorreo", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getCorreo(), dto.getClave(), dto.getPuerto(), dto.getSmtp(), dto.isTls(), dto.getEmpresa().getId());
            } else {
                service.modificar(dto.getId(), dto.getCorreo(), dto.getClave(), dto.getPuerto(), dto.getSmtp(), dto.isTls(), dto.getEmpresa().getId());
            }

            return "redirect:/cuentaCorreo/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("cuentaCorreo", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("cuentaCorreo", dto);
            return viewEdit;
        }
    }
}
