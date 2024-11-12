package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.EstadoAsistencia;
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
    private EstadoAsistencia estadoAsistencia;
    private EmpleadoDTO empleado;
}
