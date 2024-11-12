package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.EstadoMovimiento;
import com.ntm.clienteadministrativo.dto.enums.TipoMovilidad;
import com.ntm.clienteadministrativo.dto.enums.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MovimientoVisitaDTO extends BaseDTO{
    private TipoMovimiento tipoMovimiento;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fechaMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private InmuebleDTO inmueble;
    private VisitanteDTO visitante;

}
