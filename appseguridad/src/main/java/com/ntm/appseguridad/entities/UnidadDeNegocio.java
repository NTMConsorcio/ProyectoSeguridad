package com.ntm.appseguridad.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Audited
public class UnidadDeNegocio extends Base {

    @Column(unique=true)
    private String nombre;
    @ManyToOne
    private Direccion direccion;
    @ManyToOne
    private Servicio servicio;
}
