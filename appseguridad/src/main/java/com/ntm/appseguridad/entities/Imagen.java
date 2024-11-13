package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

/**
 * Clase que representa le entidad imagen
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class Imagen extends Base {
    private String nombre;
    private String mime;
    @Lob
    private byte[] contenido;
}
