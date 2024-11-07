package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.TipoTelefono;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoTelefonicoDTO extends ContactoDTO {
    private TipoTelefono tipoTelefono;
    private String telefono;
}
