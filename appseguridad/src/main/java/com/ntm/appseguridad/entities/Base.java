package com.ntm.appseguridad.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Base implements Serializable {
    @Id
    protected String id;
    ///
    ///No sale en el video pero probar si funciona.
    @Column
    protected boolean eliminado;
}
