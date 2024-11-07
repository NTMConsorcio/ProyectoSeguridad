package com.ntm.appseguridad.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServicioDTO extends BaseDTO {
    private String nombre;
    private ImagenDTO imagen;
    private EmpresaDTO empresa;
}
