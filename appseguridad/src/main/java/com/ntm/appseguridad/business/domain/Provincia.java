package com.ntm.appseguridad.business.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de provincia
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
public class Provincia implements Serializable{
    @Id
    private String id;
    private String nombre;
    private String eliminado;
    @ManyToOne
    private Pais pais;
}
