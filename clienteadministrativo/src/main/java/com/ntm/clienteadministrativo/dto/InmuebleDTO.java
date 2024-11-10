package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.EstadoInmueble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class InmuebleDTO extends BaseDTO {
    private String numeracion;
    private String piso;
    private String dpto;
    private EstadoInmueble estadoInmueble;
    private UnidadDeNegocioDTO unidadDeNegocio;
}
