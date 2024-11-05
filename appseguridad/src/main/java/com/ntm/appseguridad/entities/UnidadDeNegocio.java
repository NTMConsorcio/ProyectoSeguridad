package com.ntm.appseguridad.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDeNegocio extends Base {

    @Column(unique=true)
    private String nombre;

}
