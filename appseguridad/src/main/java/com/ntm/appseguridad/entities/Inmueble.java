package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.EstadoInmueble;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class Inmueble extends Base{
    private String numeracion;
    private String piso;
    private String dpto;
    private EstadoInmueble estadoInmueble;
    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

}
