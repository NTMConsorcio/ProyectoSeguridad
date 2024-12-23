package com.ntm.appseguridad.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
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
    @GeneratedValue
    @UuidGenerator
    protected String id;
    ///
    ///No sale en el video pero probar si funciona.
    @Column
    protected boolean eliminado;
}
