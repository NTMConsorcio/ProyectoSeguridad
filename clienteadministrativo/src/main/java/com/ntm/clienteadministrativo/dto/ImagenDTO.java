package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImagenDTO extends BaseDTO {
    private String nombre;
    private String mime;
    private byte[] contenido;
}
