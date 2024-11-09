package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.EmpresaDTO;
import com.ntm.clienteadministrativo.dto.ImagenDTO;
import com.ntm.clienteadministrativo.dto.ServicioDTO;
import com.ntm.clienteadministrativo.services.EmpresaDTOService;
import com.ntm.clienteadministrativo.services.ImagenDTOService;
import com.ntm.clienteadministrativo.services.ServicioDTOService;
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
@RequestMapping("/servicio")
public class ServicioController {
    private String viewList = "view/servicio/listServicio";
    private String viewEdit = "view/servicio/editServicio";

    @Autowired
    private ServicioDTOService service;

    @Autowired
    private ImagenDTOService imagenService;

    @Autowired
    private EmpresaDTOService empresaService;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<ServicioDTO> lista = service.listar();
            model.addAttribute("servicios", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, ServicioDTO dto) {
        try {
            model.addAttribute("isDisabled", false);
            model.addAttribute("servicio", dto);
            List<ImagenDTO> imagenesList = imagenService.listar();
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
            return "redirect:/servicio/list";
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
            ServicioDTO obj = service.buscar(id);
            model.addAttribute("servicio", obj);
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
            ServicioDTO obj = service.buscar(id);
            model.addAttribute("servicio", obj);
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
    public String aceptarEdit(Model model, ServicioDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
                model.addAttribute("servicio", dto);
                return viewEdit;
            }

            if (dto.getId() == null || dto.getId().isEmpty()) {
                service.crear(dto.getNombre(), dto.getImagen().getId(), dto.getEmpresa().getId());
            } else {
                service.modificar(dto.getId(), dto.getNombre(), dto.getImagen().getId(), dto.getEmpresa().getId());
            }

            return "redirect:/servicio/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("servicio", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("servicio", dto);
            return viewEdit;
        }
    }
}
