package com.ntm.paginaPublica.dto;

import com.ntm.paginaPublica.dto.enums.TipoTelefono;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoTelefonicoDTO extends ContactoDTO {
    private TipoTelefono tipoTelefono;
    private String telefono;
}
