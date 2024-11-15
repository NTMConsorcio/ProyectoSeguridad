package com.ntm.paginaPublica.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoCorreoElectronicoDTO extends ContactoDTO {
    private String email;
}
