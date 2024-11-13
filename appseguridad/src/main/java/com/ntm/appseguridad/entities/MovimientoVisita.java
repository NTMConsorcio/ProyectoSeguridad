package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.Base;
import com.ntm.appseguridad.entities.enums.EstadoMovimiento;
import com.ntm.appseguridad.entities.enums.TipoMovilidad;
import com.ntm.appseguridad.entities.enums.TipoMovimiento;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class MovimientoVisita extends Base {
    private Date fechaMovimiento;
    private String observacion;
    private String descripcionMovilidad;
    private TipoMovimiento tipoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private EstadoMovimiento estadoMovimiento;
    @ManyToOne
    private Inmueble inmueble;
    @ManyToOne
    private Visitante visitante;
}
