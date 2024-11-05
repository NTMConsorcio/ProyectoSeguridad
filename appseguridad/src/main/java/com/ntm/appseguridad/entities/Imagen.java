package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Clase que representa le entidad imagen
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Imagen extends Base {
    private String nombre;
    private String mime;
    private byte[] contenido;
}
