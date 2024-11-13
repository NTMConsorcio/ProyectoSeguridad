package com.ntm.appseguridad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

/**
 * Clase que representa una cuenta de correo
 * @version 1.0.0
 * @author Tomás Rando
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Audited
public class CuentaCorreo extends Base {
    private String correo;
    private String clave;
    private String puerto;
    private String smtp;
    private boolean tls;
    @OneToOne
    private Empresa empresa;

    /*
    private static CuentaCorreo cuentaCorreo;

    public CuentaCorreo obtenerCuentaCorreo() {
        if (cuentaCorreo == null) {
            cuentaCorreo = new CuentaCorreo();
        }
        return cuentaCorreo;
    }

     */
}
