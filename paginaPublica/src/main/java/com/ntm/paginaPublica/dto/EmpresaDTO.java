package com.ntm.paginaPublica.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class EmpresaDTO {
    private String nombre;
    private DireccionDTO direccion;
    private ContactoDTO contacto;
}
