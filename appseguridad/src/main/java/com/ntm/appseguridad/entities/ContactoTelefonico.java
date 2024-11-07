package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.TipoTelefono;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Clase que representa un contacto telefónico. Hereda de la clase Contacto.
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
//@SuperBuilder
public class ContactoTelefonico extends Contacto {
    private TipoTelefono tipoTelefono;
    private String telefono;
}
