package com.ntm.appseguridad.dto;

import jakarta.persistence.Lob;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImagenDTO extends BaseDTO {
    private String nombre;
    private String mime;
    @Lob
    private byte[] contenido;
}
