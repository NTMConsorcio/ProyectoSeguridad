package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

public class Persona extends Base {
    protected String id;
    protected String nombre;
    protected String apellido;
}
