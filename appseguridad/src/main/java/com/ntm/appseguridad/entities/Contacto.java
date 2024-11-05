package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entidad de contacto
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class Contacto extends Base {
    private String observacion;
    private TipoContactos tipoContacto;
}
