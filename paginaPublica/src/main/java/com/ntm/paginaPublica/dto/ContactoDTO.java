package com.ntm.paginaPublica.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ntm.paginaPublica.dto.enums.TipoContactos;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContactoTelefonicoDTO.class, name = "telefono"),
        @JsonSubTypes.Type(value = ContactoCorreoElectronicoDTO.class, name = "correo")
})
public class ContactoDTO {
    protected String observacion;
    protected TipoContactos tipoContacto;
    protected String tipo;

    public String getTipo() {
        return this instanceof ContactoTelefonicoDTO ? "telefono" : "correo";
    }
}
