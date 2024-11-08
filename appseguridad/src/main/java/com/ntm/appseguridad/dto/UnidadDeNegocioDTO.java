package com.ntm.appseguridad.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class UnidadDeNegocioDTO extends BaseDTO{
    private String nombre;
    private String direccion;
}
