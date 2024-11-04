package com.ntm.appseguridad.business.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de país
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
public class Pais implements Serializable {
    @Id
    private String id;
    private String nombre;
    private String eliminado;
}
