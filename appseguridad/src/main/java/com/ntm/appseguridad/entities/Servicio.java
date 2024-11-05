package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Clase que representa le entidad servicio
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Servicio extends Base {
    private String nombre;
    @ManyToOne
    private Imagen imagen;
    @ManyToOne
    private Empresa empresa;
}
