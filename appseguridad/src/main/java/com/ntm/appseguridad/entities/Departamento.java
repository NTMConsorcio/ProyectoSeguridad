package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad de departamento
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Departamento extends Base {

    private String nombre;
    @ManyToOne
    private Provincia provincia;
}
