package com.ntm.clienteadministrativo.controllers;


import com.ntm.clienteadministrativo.dto.ContactoCorreoElectronicoDTO;
import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.dto.EmpleadoDTO;
import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import com.ntm.clienteadministrativo.services.EmpleadoDTOService;
import com.ntm.clienteadministrativo.services.UnidadDeNegocioDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    private String viewEdit = "view/empleado/editEmpleado";
    private String viewList = "view/empleado/listEmpleado";

    @Autowired
    EmpleadoDTOService empleadoService;

    @Autowired
    UnidadDeNegocioDTOService unidadServicio;


    @GetMapping("/list")
    public String listar(Model model) {
        try {
            List<EmpleadoDTO> lista = empleadoService.listar();
            model.addAttribute("empleados", lista);

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
            empleadoService.eliminar(id);
            return "redirect:/empleado/list";
        } catch (ErrorServiceException ex) {
            model.addAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("mensajeError", "Error con el formulario");
        }
        return viewList;
    }

    @GetMapping("/nuevo")
    public String editEmpleado(Model model) throws ErrorServiceException {
        cargarCombos(model);
        model.addAttribute("empleado", new EmpleadoDTO());
        model.addAttribute("isEditMode", false);
        return viewEdit;
    }

    @PostMapping("/aceptarEdit")
    public String edit(ModelMap modelo, EmpleadoDTO dto, @RequestParam String numero, @RequestParam String correo, @RequestParam String idUnidadDeNegocio, @RequestParam(required = false) String idtel, @RequestParam(required = false) String idcorreo) throws ErrorServiceException {
        try {
            if (dto.getId() == null || dto.getId().isEmpty()) {
                empleadoService.crear(String.valueOf(dto.getDocumento()), dto.getNombre(), dto.getApellido(), numero, correo, dto.getTipoEmpleado(), idUnidadDeNegocio);
            } else {
                System.out.println(dto.getContactos());
                empleadoService.modificar(dto.getId(), String.valueOf(dto.getDocumento()), dto.getNombre(), dto.getApellido(), idtel, idcorreo, numero, correo, dto.getLegajo(), dto.getTipoEmpleado(), idUnidadDeNegocio);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/empleado/list";
    }

    @GetMapping("/modificar")
    public String modificar(Model model, @RequestParam("id") String id) {
        try {
            EmpleadoDTO obj = empleadoService.buscar(id);
            ContactoTelefonicoDTO tel = (ContactoTelefonicoDTO) obj.getContactos().get(0);
            ContactoCorreoElectronicoDTO correo = (ContactoCorreoElectronicoDTO) obj.getContactos().get(1);
            model.addAttribute("empleado", obj);
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
        List<UnidadDeNegocioDTO> unidades = unidadServicio.getActivos();
        model.addAttribute("tiposEmpleado", TipoEmpleado.values()); // Agrega los valores del enum al modelo
        model.addAttribute("unidades", unidades);
    }

}
