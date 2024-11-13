package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

/**
 * Entidad de empresa
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class Empresa extends Base {
    private String nombre;
    @OneToOne
    private Direccion direccion;
    @OneToOne
    private Contacto contacto;
}
