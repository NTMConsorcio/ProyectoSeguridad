package com.ntm.appseguridad.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ntm.appseguridad.entities.enums.TipoTelefono;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactoTelefonicoDTO extends ContactoDTO {
    private TipoTelefono tipoTelefono;
    private String telefono;
    private String type;
}
