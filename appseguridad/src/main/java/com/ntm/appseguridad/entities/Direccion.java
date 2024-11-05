package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad de direccion
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Direccion extends Base {
    private String calle;
    private String numeracion;
    private String latitud;
    private String longitud;
    @ManyToOne
    private Localidad provincia;
}
