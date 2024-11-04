package com.ntm.appseguridad.business.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDeNegocio extends Base {

    @Column(unique=true)
    private String nombre;

}
