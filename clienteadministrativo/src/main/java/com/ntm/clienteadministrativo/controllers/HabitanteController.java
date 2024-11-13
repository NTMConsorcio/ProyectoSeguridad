package com.ntm.clienteadministrativo.controllers;


import com.ntm.clienteadministrativo.dto.ContactoCorreoElectronicoDTO;
import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.dto.HabitanteDTO;
import com.ntm.clienteadministrativo.dto.InmuebleDTO;
import com.ntm.clienteadministrativo.services.HabitanteDTOService;
import com.ntm.clienteadministrativo.services.InmuebleDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/habitante")
public class HabitanteController {
    private String viewEdit = "view/habitante/editHabitante";
    private String viewList = "view/habitante/listHabitante";

    @Autowired
    HabitanteDTOService habitanteService;

    @Autowired
    InmuebleDTOService unidadInmueble;


    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<HabitanteDTO> lista = habitanteService.listar();
            model.addAttribute("habitantes", lista);

        } catch (ErrorServiceException e) {
            model.addAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error de Sistema");
        }
        return viewList;
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value = "id") String id, Model model) {
        try {
            habitanteService.eliminar(id);
            return "redirect:/habitante/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error con el formulario");
        }
        return viewList;
    }

    @GetMapping("/nuevo")
    public String editHabitante(Model model) throws ErrorServiceException {
        cargarCombos(model);
        model.addAttribute("habitante", new HabitanteDTO());
        model.addAttribute("isEditMode", false);
        return viewEdit;
    }

    @GetMapping("/consultar")
    public String consultar(Model model, @RequestParam("id") String id) {
        try {
            HabitanteDTO obj = habitanteService.buscar(id);
            ContactoTelefonicoDTO tel = (ContactoTelefonicoDTO) obj.getContactos().get(0);
            ContactoCorreoElectronicoDTO correo = (ContactoCorreoElectronicoDTO) obj.getContactos().get(1);
            model.addAttribute("habitante", obj);
            model.addAttribute("numero", tel.getTelefono());
            model.addAttribute("correo", correo.getEmail());
            model.addAttribute("isDisabled", true);
            cargarCombos(model);
            model.addAttribute("isEditMode", false);
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

    @PostMapping("/aceptarEdit")
    public String edit(ModelMap modelo, HabitanteDTO dto, @RequestParam("numero") String numero, @RequestParam("correo") String correo, @RequestParam("idInmueble") String idInmueble, @RequestParam(name="idtel", required = false) String idtel, @RequestParam(name="idcorreo", required = false) String idcorreo, BindingResult result) throws ErrorServiceException {
        try {
            if (dto.getId() == null || dto.getId().isEmpty()) {
                habitanteService.crear(String.valueOf(dto.getDocumento()), dto.getNombre(), dto.getApellido(), numero, correo, idInmueble);
            } else {
                habitanteService.modificar(dto.getId(), String.valueOf(dto.getDocumento()), dto.getNombre(), dto.getApellido(), idtel, idcorreo, numero, correo, idInmueble);
            }
            return "redirect:/habitante/list";
        } catch (ErrorServiceException ex) {
            modelo.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception e) {
            modelo.addAttribute("mensajeError", "Error con el envío");
        }
        List<InmuebleDTO> inmuebles = unidadInmueble.listar();
        modelo.addAttribute("inmuebles", inmuebles);
        modelo.addAttribute("habitante, dto");
        return viewEdit;
    }

    @GetMapping("/modificar")
    public String modificar(Model model, @RequestParam("id") String id) {
        try {
            HabitanteDTO obj = habitanteService.buscar(id);
            System.out.println(obj.getId());
            ContactoTelefonicoDTO tel = (ContactoTelefonicoDTO) obj.getContactos().get(0);
            ContactoCorreoElectronicoDTO correo = (ContactoCorreoElectronicoDTO) obj.getContactos().get(1);
            model.addAttribute("habitante", obj);
            model.addAttribute("numero", tel.getTelefono());
            model.addAttribute("correo", correo.getEmail());
            model.addAttribute("isDisabled", false);
            cargarCombos(model);
            model.addAttribute("isEditMode", true);
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

    public void cargarCombos(Model model) throws ErrorServiceException {
        List<InmuebleDTO> inmuebles = unidadInmueble.listar();
        model.addAttribute("inmuebles", inmuebles);
    }

}
