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

    @GetMapping("/inicio")
    public String inicio(Model model) {
        return "view/indexHabitante";
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

    @PostMapping("/aceptarEdit")
    public String edit(ModelMap modelo, HabitanteDTO dto, @RequestParam String numero, @RequestParam String correo, @RequestParam String idInmueble, @RequestParam(required = false) String idtel, @RequestParam(required = false) String idcorreo) throws ErrorServiceException {
        try {
            if (dto.getId() == null || dto.getId().isEmpty()) {
                habitanteService.crear(String.valueOf(dto.getDocumento()), dto.getNombre(), dto.getApellido(), numero, correo, idInmueble);
            } else {
                System.out.println(dto.getContactos());
                habitanteService.modificar(dto.getId(), String.valueOf(dto.getDocumento()), dto.getNombre(), dto.getApellido(), idtel, idcorreo, numero, correo, idInmueble);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "view/index";
    }

    @GetMapping("/modificar")
    public String modificar(Model model, @RequestParam("id") String id) {
        try {
            HabitanteDTO obj = habitanteService.buscar(id);
            ContactoTelefonicoDTO tel = (ContactoTelefonicoDTO) obj.getContactos().get(0);
            ContactoCorreoElectronicoDTO correo = (ContactoCorreoElectronicoDTO) obj.getContactos().get(1);
            model.addAttribute("habitante", obj);
            model.addAttribute("numero", tel.getTelefono());
            System.out.println(tel.getTelefono());
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
        System.out.println(inmuebles);
        model.addAttribute("inmuebles", inmuebles);
    }

}
