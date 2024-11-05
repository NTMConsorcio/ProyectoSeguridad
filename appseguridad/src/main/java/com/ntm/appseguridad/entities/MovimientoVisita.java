package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.Base;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class MovimientoVisita extends Base {
    private Date fechaMovimiento;
    private String observacion;
    private String descripcionMovilidad;
}
