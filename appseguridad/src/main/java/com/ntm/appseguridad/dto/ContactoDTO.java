package com.ntm.appseguridad.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ntm.appseguridad.entities.enums.TipoContactos;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContactoTelefonicoDTO.class, name = "telefono"),
        @JsonSubTypes.Type(value = ContactoCorreoElectronicoDTO.class, name = "correo")
})
public class ContactoDTO extends BaseDTO {
    protected String observacion;
    protected TipoContactos tipoContacto;
}
