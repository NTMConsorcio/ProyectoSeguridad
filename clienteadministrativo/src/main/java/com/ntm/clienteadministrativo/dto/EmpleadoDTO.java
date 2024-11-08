package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.TipoEmpleado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class EmpleadoDTO extends PersonaDTO{
    public String legajo;
    public TipoEmpleado tipoEmpleado;
    public UnidadDeNegocioDTO unidadDeNegocio;
}

