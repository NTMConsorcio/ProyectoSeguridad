package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class UnidadDeNegocioDTO extends BaseDTO{
    private String nombre;
    private DireccionDTO direccion;
    private ServicioDTO servicio;
}
