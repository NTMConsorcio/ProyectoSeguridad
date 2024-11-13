package com.ntm.appseguridad.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class Persona extends Base {
    protected int documento;
    protected String nombre;
    protected String apellido;
    @ManyToMany(cascade = CascadeType.PERSIST)
    protected List<Contacto> contactos;
    @ManyToOne
    protected Usuario usuario;
}
