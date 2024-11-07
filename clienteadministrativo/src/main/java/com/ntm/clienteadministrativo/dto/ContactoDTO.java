package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.TipoContactos;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoDTO extends BaseDTO {
    protected String observacion;
    protected TipoContactos tipoContacto;
}
