package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Clase que representa un contacto de correo electrónico. Hereda de la clase Contacto.
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
public class ContactoCorreoElectronico extends Contacto {
    private String email;    
}
