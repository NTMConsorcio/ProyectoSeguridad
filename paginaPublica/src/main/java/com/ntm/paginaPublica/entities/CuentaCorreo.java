package com.ntm.paginaPublica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class CuentaCorreo {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    @Column(unique=true)
    private String correo;
    private boolean eliminado;
}
