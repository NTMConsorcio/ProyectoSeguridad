package com.ntm.appseguridad.dto;


import com.ntm.appseguridad.entities.enums.TipoEmpleado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

public class EmpleadoDTO extends PersonaDTO{
    public String legajo;
    public TipoEmpleado tipoEmpleado;
    public List<ContactoDTO> contactos;
    public UnidadDeNegocioDTO unidadDeNegocio;
}

