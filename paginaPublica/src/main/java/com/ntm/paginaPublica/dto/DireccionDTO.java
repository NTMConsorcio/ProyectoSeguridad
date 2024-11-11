package com.ntm.paginaPublica.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class DireccionDTO {
    private String calle;
    private String numeracion;
    private String latitud;
    private String longitud;
    private LocalidadDTO localidad;
}
