package com.ntm.appseguridad.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DireccionDTO extends BaseDTO {
    private String calle;
    private String numeracion;
    private String latitud;
    private String longitud;
    private LocalidadDTO localidad;
}
