package com.ntm.appseguridad.business.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
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
@SuperBuilder
public class Contacto implements Serializable {
    @Id
    private String id;
    private String observacion;
    private Contacto tipoContacto;
    private String eliminado;
}
