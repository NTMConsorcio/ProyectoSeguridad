package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.ProvinciaDTO;
import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import com.ntm.clienteadministrativo.services.ProvinciaDTOService;
import com.ntm.clienteadministrativo.services.ContactoTelefonicoDTOService;
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
@RequestMapping("/contactoTelefonico")
public class ContactoTelefonicoController {
    private String viewList = "view/contactoTelefonico/listContactoTelefonico";
    private String viewEdit = "view/contactoTelefonico/editContactoTelefonico";

    @Autowired
    private ContactoTelefonicoDTOService service;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<ContactoTelefonicoDTO> lista = service.listar();
            model.addAttribute("contactosTelefonicos", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, ContactoTelefonicoDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("contactoTelefonico", dto);
            return viewEdit;
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistemas");
        }
        return viewList;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value="id") String id, Model model) {
        try {
            service.eliminar(id);
            return "redirect:/contactoTelefonico/list";
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
            ContactoTelefonicoDTO obj = service.buscar(id);
            model.addAttribute("contactoTelefonico", obj);
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
            ContactoTelefonicoDTO obj = service.buscar(id);
            model.addAttribute("contactoTelefonico", obj);
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
    public String aceptarEdit(Model model, ContactoTelefonicoDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("contactoTelefonico", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getObservacion(), TipoContactos.PERSONAL, dto.getTelefono(), TipoTelefono.CELULAR);
            } else {
                service.modificar(dto.getId(), dto.getObservacion(), TipoContactos.PERSONAL, dto.getTelefono(), TipoTelefono.CELULAR);
            }

            return "redirect:/contactoTelefonico/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("contactoTelefonico", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("contactoTelefonico", dto);
            return viewEdit;
        }
    }
}
