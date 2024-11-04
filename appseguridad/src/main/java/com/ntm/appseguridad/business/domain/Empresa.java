package com.ntm.appseguridad.business.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de empresa
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
public class Empresa implements Serializable {
    @Id
    private String id;
    private String nombre;
    private String eliminado;
    @OneToOne
    private Direccion direccion;
    @OneToOne
    private Empresa empresa;
}
