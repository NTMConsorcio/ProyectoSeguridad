package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.EstadoAsistencia;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class PlanillaHoraria extends Base{
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date salida;
    private EstadoAsistencia estadoAsistencia;
    @ManyToOne
    private Empleado empleado;
}
