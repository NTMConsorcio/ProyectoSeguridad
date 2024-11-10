package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.TipoEmpleado;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

public class Empleado extends Persona{

    public String legajo;
    public TipoEmpleado tipoEmpleado;
    @ManyToOne
    public UnidadDeNegocio unidadDeNegocio;
}
