package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class EmpleadoDTO extends PersonaDTO{
    public String legajo;
    public String tipoEmpleado;
    public String idUnidadDeNegocio;
}

