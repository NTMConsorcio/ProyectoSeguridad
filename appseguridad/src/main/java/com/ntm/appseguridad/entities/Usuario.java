package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Audited
public class Usuario extends Base{
    public String cuenta;
    public String clave;
    @Enumerated(EnumType.STRING)
    public Rol rol;
}
