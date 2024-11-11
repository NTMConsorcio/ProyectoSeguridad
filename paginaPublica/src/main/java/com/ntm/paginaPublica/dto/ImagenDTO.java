package com.ntm.paginaPublica.dto;

import lombok.Data;

@Data
public class ImagenDTO {
    private String nombre;
    private String mime;
    private byte[] contenido;
}
