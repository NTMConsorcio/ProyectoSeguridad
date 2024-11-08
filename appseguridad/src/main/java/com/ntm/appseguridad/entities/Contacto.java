package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.TipoContactos;
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
public class Contacto extends Base {
    protected String observacion;
    protected TipoContactos tipoContacto;
}
