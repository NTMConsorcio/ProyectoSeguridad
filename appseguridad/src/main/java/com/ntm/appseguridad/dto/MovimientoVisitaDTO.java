package com.ntm.appseguridad.dto;

import com.ntm.appseguridad.entities.enums.EstadoMovimiento;
import com.ntm.appseguridad.entities.enums.TipoMovilidad;
import com.ntm.appseguridad.entities.enums.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MovimientoVisitaDTO extends BaseDTO{
    private TipoMovimiento tipoMovimiento;
    private Date fechaMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private InmuebleDTO inmueble;
    private VisitanteDTO visitante;

}
