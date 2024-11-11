package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.*;
import com.ntm.clienteadministrativo.services.ContactoCorreoElectronicoDTOService;
import com.ntm.clienteadministrativo.services.ContactoTelefonicoDTOService;
import com.ntm.clienteadministrativo.services.DireccionDTOService;
import com.ntm.clienteadministrativo.services.EmpresaDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/empresa")
public class EmpresaController {
    private String viewList = "view/empresa/listEmpresa";
    private String viewEdit = "view/empresa/editEmpresa";

    @Autowired
    private EmpresaDTOService service;

    @Autowired
    private DireccionDTOService direccionService;

    @Autowired
    private ContactoTelefonicoDTOService contactoTelefonicoService;

    @Autowired
    private ContactoCorreoElectronicoDTOService contactoCorreoService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<EmpresaDTO> lista = service.listar();
            model.addAttribute("empresas", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, EmpresaDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("empresa", dto);
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
            return "redirect:/empresa/list";
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
            EmpresaDTO obj = service.buscar(id);
            model.addAttribute("empresa", obj);
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
            EmpresaDTO obj = service.buscar(id);
            model.addAttribute("empresa", obj);
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
    public String aceptarEdit(Model model, EmpresaDTO dto, BindingResult result, RedirectAttributes attributes, @RequestParam("tipoContacto") String tipo) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
            } else {
                if (dto.getId() == null || dto.getId().isEmpty()) {
                    service.crear(dto.getNombre(), dto.getDireccion().getId(), dto.getContacto().getId(), tipo);
                } else {
                    service.modificar(dto.getId(), dto.getNombre(), dto.getDireccion().getId(), dto.getContacto().getId(), tipo);
                }

                return "redirect:/empresa/list";
            }
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
        }
        model.addAttribute("empresa", dto);
        cargarListas(model);
        return viewEdit;
    }

    public void cargarListas(Model model) throws ErrorServiceException {
        List<DireccionDTO> direccionesList = direccionService.listar();
        List<ContactoCorreoElectronicoDTO> correosList = contactoCorreoService.listar();
        List<ContactoTelefonicoDTO> telefonosList = contactoTelefonicoService.listar();
        List<ContactoDTO> contactosList = new ArrayList<>();
        contactosList.addAll(correosList);
        contactosList.addAll(telefonosList);
        model.addAttribute("contactos", contactosList);
        model.addAttribute("direcciones", direccionesList);
    }
}
