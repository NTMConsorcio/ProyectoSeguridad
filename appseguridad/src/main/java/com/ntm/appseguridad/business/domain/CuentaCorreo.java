package com.ntm.appseguridad.business.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una cuenta de correo
 * @version 1.0.0
 * @autor Tomás Rando
 */
@Entity
@NoArgsConstructor
@Data
public class CuentaCorreo implements Serializable {
    @Id
    private String id;
    private String correo;
    private String clave;
    private String puerto;
    private String smtp;
    private boolean tls;
    private boolean eliminado;
    @OneToOne
    private Empresa empresa;
}
