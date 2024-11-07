package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmpresaDTO extends BaseDTO {
    private String nombre;
    private DireccionDTO direccion;
    private ContactoDTO contacto;
}
