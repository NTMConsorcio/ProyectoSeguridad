package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class Visitante extends Base{
    private String nombre;
    private String apellido;
    private int numeroDeDocumento;

}
