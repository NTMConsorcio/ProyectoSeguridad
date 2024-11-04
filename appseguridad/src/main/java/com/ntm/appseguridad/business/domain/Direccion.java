package com.ntm.appseguridad.business.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de direccion
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
public class Direccion implements Serializable {
    @Id
    private String id;
    private String calle;
    private String numeracion;
    private String eliminado;
    private String latitud;
    private String longitud;
    @ManyToOne
    private Localidad provincia;
}
