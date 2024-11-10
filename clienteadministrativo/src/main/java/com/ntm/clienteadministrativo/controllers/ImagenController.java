package com.ntm.clienteadministrativo.controllers;

import com.ntm.clienteadministrativo.dto.ImagenDTO;
import com.ntm.clienteadministrativo.services.ImagenDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/imagen")
public class ImagenController {
    private String viewList = "view/imagen/listImagen";
    private String viewEdit = "view/imagen/editImagen";

    @Autowired
    private ImagenDTOService service;

    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<ImagenDTO> lista = service.listar();
            Map<String, String> newList = service.obtenerImagenesEnBase64(lista);
            model.addAttribute("imagenes", lista);
            model.addAttribute("imagenesBase", newList);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/alta")
    public String alta(Model model, ImagenDTO dto) {
        model.addAttribute("isDisabled", false);
        model.addAttribute("imagen", dto);
        return viewEdit;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value = "id") String id, Model model) {
        try {
            service.eliminar(id);
            return "redirect:/imagen/list";
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
            ImagenDTO obj = service.buscar(id);
            model.addAttribute("imagen", obj);
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
            ImagenDTO obj = service.buscar(id);
            model.addAttribute("imagen", obj);
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
    public String aceptarEdit(@RequestParam("name") String nombre, @RequestParam("archivo") MultipartFile archivo, Model model, ImagenDTO dto, BindingResult result, RedirectAttributes attributes) throws ErrorServiceException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("mensajeError", "Error en el formulario");
            } else {

                if (dto.getId() == null || dto.getId().isEmpty()) {
                    service.crear(nombre, archivo);
                }

                return "redirect:/imagen/list";
            }
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
            model.addAttribute("imagen", dto);
            return viewEdit;
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error en el formulario");
            model.addAttribute("imagen", dto);
            return viewEdit;
        }
        model.addAttribute("imagen", dto);
        return viewEdit;
    }
}
