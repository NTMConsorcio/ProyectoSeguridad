package com.ntm.clienteadministrativo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class VisitanteDTO extends BaseDTO{
    private String nombre;
    private String apellido;
    private int numeroDeDocumento;

}
