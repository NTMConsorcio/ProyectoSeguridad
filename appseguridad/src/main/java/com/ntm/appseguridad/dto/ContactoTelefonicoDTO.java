package com.ntm.appseguridad.dto;


import com.ntm.appseguridad.entities.enums.TipoTelefono;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoTelefonicoDTO extends ContactoDTO {
    private TipoTelefono tipoTelefono;
    private String telefono;
}
