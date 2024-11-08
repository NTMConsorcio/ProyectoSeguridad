package com.ntm.appseguridad.dto;


import com.ntm.appseguridad.entities.enums.TipoContactos;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoDTO extends BaseDTO {
    protected String observacion;
    protected TipoContactos tipoContacto;
}
