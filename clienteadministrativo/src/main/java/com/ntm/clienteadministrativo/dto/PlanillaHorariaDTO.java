package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class PlanillaHorariaDTO extends BaseDTO {
    private Date entrada;
    private Date salida;
    private String estadoAsistencia;
    private String idEmpleado;
}
